package com.crudtest.controller;

import com.crudtest.model.User;
import com.crudtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("users", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView addUser(User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("result");
        }else {
            userService.saveUser(user);
            modelAndView.addObject("users", new User());
            modelAndView.setViewName("redirect:result");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView viewUsers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("result");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute User updateobject, Model model){
        model.addAttribute("updateobject", updateobject);
        return "update";
    }
    @RequestMapping("/updateuser")
    public String updateService(@ModelAttribute("updateobject") User user){
        userService.saveUser(user);
        return "redirect:result";
    }

}
