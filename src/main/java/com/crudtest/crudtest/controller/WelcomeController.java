package com.crudtest.crudtest.controller;

import com.crudtest.crudtest.form.User;
import com.crudtest.crudtest.domain.UserEntity;
import com.crudtest.crudtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class WelcomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String welcome(User user) {
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "welcome";
        }
        userRepository.save(new UserEntity(user.getFirstName(), user.getLastName()));
        model.addAttribute("users", userRepository.findAll());
        return "redirect:result";
    }
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String showUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "result";
    }
}