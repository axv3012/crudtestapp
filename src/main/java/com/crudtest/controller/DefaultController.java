package com.crudtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
    @RequestMapping("/")
    public ModelAndView welcome() {
        return new ModelAndView("welcome");
    }

    @RequestMapping("/denied")
    public ModelAndView denied() {
        return new ModelAndView("denied");
    }
}
