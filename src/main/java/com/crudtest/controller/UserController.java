package com.crudtest.controller;

import com.crudtest.model.User;
import com.crudtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("users", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/index", method = RequestMethod.POST)
    public ModelAndView addUser(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.getUserByLastName(user.getLastName());
        if(userExists!=null){
            bindingResult.rejectValue("lastName","error.user","This last name already exists");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("index");
        }else {
            userService.saveUser(user);
            modelAndView.addObject("users", new User());
            modelAndView.setViewName("index");
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

}
