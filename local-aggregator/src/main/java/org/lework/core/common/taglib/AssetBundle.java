package org.lework.core.common.taglib;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.lework.core.common.AppConfigConstant;
import org.lework.runner.security.Digests;
import org.lework.runner.utils.Encodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 自定义jsp2.0标签,绑定标签
 * 绑定同类型子标签（javascript，stylesheet）到单独文件
 * <p>
 * 属性说明：
 * name
 * </p>
 * User:Gongle
 * Date: 2013-10-01 18:51:48
 */
public class AssetBundle extends BodyTagSupport {
    private static Logger logger = LoggerFactory.getLogger(AssetBundle.class);
    public static final String DEFAULT_PATH_SYMBOL = "/";
    //使用GuavaCache,最大缓存个数:100,过期时间:3小时
    public static final LoadingCache<String, String> cache;

    static {
        cache = CacheBuilder.newBuilder().maximumSize(100)
                .expireAfterAccess(3, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        //计算sha1 value
                        byte[] salt = Digests.generateSalt(8);
                        byte[] sha1Result = Digests.sha1(key.getBytes(), salt);
                        String sha1 = Encodes.encodeHex(sha1Result);
                       // logger.info("sha1 in hex result with salt  : " + sha1);
                        logger.info("cache key:{},value:{}", key, sha1);
                        return sha1;
                    }

                });
    }

    private String name;
    /**
     * 合并文件的个数*
     */
    private Integer size = 10;
    private List<AssetJavascript> scripts = new ArrayList<AssetJavascript>();
    private List<AssetStylesheet> stylesheets = new ArrayList<AssetStylesheet>();


    @Override
    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        JspWriter out = bc.getEnclosingWriter();
        try {
            //存在缓存则直接返回

            //合并File to InputStream

            out.print(name);
            out.print(scripts.toString());
            out.print(stylesheets.toString());
        } catch (IOException e) {
            throw new JspException("Error:" + e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    /**
     * 压缩资源文件 => 写入到应用static\min目录 =>
     *
     * @param in 资源文件输入流
     * @return
     */
    private File compressorAndWrite(InputStream in) {
        return null;
    }

    /**
     * 根据标签资源描述信息,读取=>合并到一个输出流.
     *
     * @param resourceMetaList
     * @return
     */
    private InputStream mergeResourceAsOne(List<ITagResourceMeta> resourceMetaList) throws FileNotFoundException {
        Vector<FileInputStream> srcFiles = new Vector<FileInputStream>();
        ITagResourceMeta rs;
        InputStream input;
        for (int i = 0; i < resourceMetaList.size(); i++) {
            rs = resourceMetaList.get(i);
            String path = FilenameUtils.concat(AppConfigConstant.REAL_PATH, rs.getSrc());
            File file = new File(path);
            if (!file.exists()) {
                logger.warn("file:{} no exist ", path);
                continue;
            }
            logger.debug("预合并文件:{}", path);
            srcFiles.add(new FileInputStream(file));
        }
        if (srcFiles.size() > 1) {
            input = new SequenceInputStream(srcFiles.elements());
        } else {
            input = srcFiles.get(0);
        }
        return input;
    }

    //添加一个Script标签
    public void addScript(AssetJavascript script) {
        scripts.add(script);
    }

    //添加一个StyleSheet标签
    public void addStylesheet(AssetStylesheet stylesheet) {
        stylesheets.add(stylesheet);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
