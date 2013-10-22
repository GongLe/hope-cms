package org.lework.core.common;

import javax.servlet.ServletContext;

import org.lework.runner.utils.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

/**
 * 容器初始化设置的ServletContext级别的变量ctx,src
 *
 * @author Gongle
 */
public class ResourcePathExposer implements ServletContextAware {
    private static Logger logger = LoggerFactory.getLogger(ResourcePathExposer.class);
    public static final String DEFALUT_CONTEXT = ".";

    public ResourcePathExposer() {
    }

    private ServletContext servletContext;


    /*
     * 'ctx':'${ctx}',
                'src':'${src}',
                'appName': "/<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>",
                'appImg': "http://<%= request.getServerName() %>:<%= request.getServerPort() %>${src}/images",
                'appSrc': "http://<%= request.getServerName() %>:<%= request.getServerPort() %>${src}"
     */
    public void init() {
        //配置为本地上下文根
        if (Strings.startsWith(AppConfigConstant.CTX, DEFALUT_CONTEXT)) {
            AppConfigConstant.CTX = getServletContext().getContextPath();
        }
        if (Strings.startsWith(AppConfigConstant.SRC, DEFALUT_CONTEXT)) {
            AppConfigConstant.SRC = getServletContext().getContextPath() + "/static";
        }

        getServletContext().setAttribute("src", AppConfigConstant.SRC);
        getServletContext().setAttribute("ctx", AppConfigConstant.CTX);
        logger.info("ctx:{}", AppConfigConstant.CTX);
        logger.info("src:{}", AppConfigConstant.SRC);
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }


}
