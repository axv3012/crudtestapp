package com.crudtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView("login");

        return modelAndView;
    }

    @RequestMapping(value = "/loggedout", method = RequestMethod.GET)
    public ModelAndView logout() {
        return new ModelAndView("welcome");
    }


}
