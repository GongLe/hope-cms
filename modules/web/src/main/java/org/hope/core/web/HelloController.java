package org.hope.core.web;


import org.hope.core.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
    @Autowired
    private AccountService accountSerivce;

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        accountSerivce.getAllUser();
        return "hello";
    }


}
