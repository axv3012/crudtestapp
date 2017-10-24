package com.crudtest.controller;

import com.crudtest.form.UserForm;
import com.crudtest.form.validator.UserValidator;
import com.crudtest.model.CurrentUser;
import com.crudtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        UserForm form = new UserForm();
        modelAndView.addObject("form", form);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("form") @Valid UserForm form, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.findOneByEmail(form.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "email", "There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else {
            userService.saveUser(form);
            modelAndView.setViewName("redirect:/");
        }
        modelAndView.addObject("form", form);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/result")
    public ModelAndView viewUsers() {
        return new ModelAndView("result", "form", userService.findAll());
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute UserForm updateobject) {
        return new ModelAndView("update", "updateobject", updateobject);
    }

    @RequestMapping(value = "/updateuser")
    public ModelAndView updateService(@ModelAttribute("updateobject") UserForm form) {
        ModelAndView modelAndView = new ModelAndView();

        CurrentUser userDetails =
                (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getRole().toString());
        if (userDetails.getRole().toString() == "ADMIN") {
            modelAndView.setViewName("redirect:result");
        } else if (userDetails.getRole().toString() == "USER") {
            modelAndView.setViewName("redirect:userinfo");
        }

        userService.updateUser(form);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ModelAndView approve(@ModelAttribute UserForm approveobject) {
        return new ModelAndView("approve", "approveobject", approveobject);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/approveuser")
    public ModelAndView approveService(@ModelAttribute("approveobject") UserForm form) {
        userService.adminApprove(form);
        return new ModelAndView("redirect:result");
    }


    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/userinfo")
    public ModelAndView userInfo() {
        ModelAndView modelAndView = new ModelAndView();
        CurrentUser userDetails =
                (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails.getUser().isApproved()) {
            modelAndView.addObject("userobject", userService.findUserById(userDetails.getUser().getId()));
            modelAndView.setViewName("userinfo");
        } else {
            modelAndView.setViewName("redirect:denied");
        }
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete")
    public ModelAndView deleteUser(@ModelAttribute UserForm updateobject){
        userService.deleteUser(updateobject);
        return new ModelAndView("redirect:result");
    }
}
