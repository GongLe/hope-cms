package org.lework.core.web.organization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Organization Controller
 * User: Gongle
 * Date: 13-10-22
 */
@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "organization/organization";
    }
}
