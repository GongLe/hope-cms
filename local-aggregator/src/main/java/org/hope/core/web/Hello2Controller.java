package org.hope.core.web;

import org.hope.core.entity.user.User;
import org.hope.core.service.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/hello2")
public class Hello2Controller {
    @Autowired
    private AccountService accountSerivce;
    Logger logger = LoggerFactory.getLogger(Hello2Controller.class) ;
    @RequestMapping(method = RequestMethod.GET)
    public  String hello(@PageableDefaults(pageNumber = 0, value = 30) Pageable pageable){
        logger.info(">>>> hello action !");
        logger.info("spring.profiles.active:{}", System.getProperty("spring.profiles.active"));
        logger.info("spring.profiles.dafault:{}", System.getProperty("spring.profiles.default"));
        User user = new User();
        user.setName( "测试用户");
        user.setLoginName("test" + Math.random());
        user.setPlainPassword("123456");
      //  user.setUserRegistered(new Date());
        accountSerivce.getAllUser() ;
        accountSerivce.registerUser(user);
        logger.info(String.valueOf(accountSerivce.getPageUser(pageable).getTotalPages()) ); ;
        logger.info(user.toString());
        return  "hello" ;
    }
}
