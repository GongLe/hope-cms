package org.lework.core.common.taglib;

import org.lework.core.common.AppConfigConstant;
import org.lework.runner.utils.Strings;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义jsp2.0标签，封装html script标签。
 * 1）当javascript标签单存在时，生成原生：<script ***
 * 2）当与bundle标签结合使用时，同类型的script、stylesheet标签会合并到当个文件里面去。
 * <p>
 * 属性说明：
 * src 必填
 * type 非必填 默认值：text/javascript
 * </p>
 * User:Gongle
 * Date: 2013-10-01 18:51:48
 */
public class AssetJavascript extends SimpleTagSupport {
    public static final String DEFAULT_PATH_SYMBOL = "/";
    public static final String DEFAULT_TYPE = "text/javascript";
    public static final String DEFAULT_OUT = "<script src=\"%s\" type=\"%s\" ></script> ";
    private String src;
    private String type;
    private AssetBundle parent;
    @Override
    public void doTag() throws JspException, IOException {
        this.type = Strings.defaultString(getType(), DEFAULT_TYPE);
        if (getParent() != null && getParent() instanceof AssetBundle) {
            parent = (AssetBundle) getParent();
            parent.addScript(this);
        } else {
            getJspContext().getOut().write(buildOut());
        }
    }

    public String buildOut() {
        return String.format(String.format(DEFAULT_OUT, getRealPath(), getType()));
    }

    private String getRealPath() {
        return AppConfigConstant.SRC + (getSrc().startsWith(DEFAULT_PATH_SYMBOL) ? getSrc() : DEFAULT_PATH_SYMBOL + getSrc());
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
