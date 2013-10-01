package org.hope.core.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public static final String DEFAULT_PATH_SYMBOL = "/";
    private String name;
    private List<AssetJavascript> scripts = new ArrayList<AssetJavascript>();
    private List<AssetStylesheet> stylesheets = new ArrayList<AssetStylesheet>();


    @Override
    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        JspWriter out = bc.getEnclosingWriter();
        try {
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
}
