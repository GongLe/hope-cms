package org.lework.core.web.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Menu Controller
 * User: Gongle
 * Date: 13-10-22
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "menu/menu";
    }
}
