package com.webfactory.springbootdemo.demoproject.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home.jsp";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login.jsp";
    }

    @RequestMapping("/logout-success")
    public String lougoutPage(){
        return "logout.jsp";
    }
}
