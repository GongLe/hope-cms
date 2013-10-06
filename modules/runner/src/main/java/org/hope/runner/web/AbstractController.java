package org.hope.runner.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Spring mvc Abstract Controller
 */
public abstract class AbstractController {
    /**
     * 提示类别*
     */
    protected static final String notify_type_info = "info";
    protected static final String notify_type_success = "success";
    protected static final String notify_type_error = "error";
    protected static final String default_title = "提示消息";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 提示消息
     *
     * @param model   参数存放的model对象
     * @param message 消息内容
     */
    protected void prompt(Model model, String message) {
        prompt(model, null, message, notify_type_info);
    }
    /**
     * 提示消息
     *
     * @param redirectAttributes   参数存放的RedirectAttributes对象
     * @param message 消息内容
     */
    protected void prompt(RedirectAttributes redirectAttributes, String message) {
        prompt(redirectAttributes, null, message, notify_type_info);
    }

    /**
     * 提示消息
     *
     * @param model   参数存放的model对象
     * @param title   消息提示框标题
     * @param message 消息内容
     */
    protected void prompt(Model model, String title, String message, String type) {
        model.addAttribute("$title", StringUtils.isEmpty(title) ? default_title : title);
        model.addAttribute("$message", message);
        model.addAttribute("$type", StringUtils.isEmpty(type) ? notify_type_info : type);
    }
    /**
     * 提示消息
     *
     * @param redirectAttributes   参数存放的RedirectAttributes对象
     * @param title   消息提示框标题
     * @param message 消息内容
     */
    protected void prompt(RedirectAttributes redirectAttributes, String title, String message, String type) {
        redirectAttributes.addFlashAttribute("$title", StringUtils.isEmpty(title) ? default_title : title);
        redirectAttributes.addFlashAttribute("$message", message);
        redirectAttributes.addFlashAttribute("$type", StringUtils.isEmpty(type) ? notify_type_info : type);
    }


}
