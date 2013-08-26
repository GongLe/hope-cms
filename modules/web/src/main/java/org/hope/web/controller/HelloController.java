package org.hope.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
@Controller
@RequestMapping(value = "/hello")
public class HelloController {
      public String hello(){
          return  "hello" ;
      }
}
