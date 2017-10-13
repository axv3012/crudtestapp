package com.crudtest.controller;

import com.crudtest.form.UserForm;
import com.crudtest.form.validator.UserValidator;
import com.crudtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;


    @InitBinder("form")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        UserForm form = new UserForm();
        modelAndView.addObject("form", form);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("form") @Valid UserForm form, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.findByEmail(form.getEmail()) != null) {
            bindingResult.rejectValue("email", "email", "There is already a user registered with the email provided.");
            modelAndView.setViewName("index");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("index");
        } else {
            userService.saveUser(form);
            modelAndView.setViewName("redirect:result");
        }
        modelAndView.addObject("form", form);
        return modelAndView;
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView viewUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("form", userService.findAll());
        modelAndView.setViewName("result");
        return modelAndView;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(@ModelAttribute UserForm updateobject) {
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("updateobject", updateobject);
        return modelAndView;
    }

    @RequestMapping("/updateuser")
    public ModelAndView updateService(@ModelAttribute("updateobject") UserForm form) {
        ModelAndView modelAndView = new ModelAndView("redirect:result");
        userService.saveUser(form);
        return modelAndView;
    }

}
