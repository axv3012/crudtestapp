package com.crudtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/loggedout", method = RequestMethod.GET)
    public ModelAndView logout() {
        return new ModelAndView("welcome");
    }

    @RequestMapping(value = "/loginfailure", method = RequestMethod.GET)
    public ModelAndView loginFailed() {
        return new ModelAndView("login", "loginError", true);
    }


}
