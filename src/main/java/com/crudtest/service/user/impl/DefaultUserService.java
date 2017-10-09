package com.crudtest.service.user.impl;

import com.crudtest.model.User;
import com.crudtest.repository.UserRepository;
import com.crudtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    List<User> users = new ArrayList<User>();


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }


    @Override
    public List<User> findAll() {
        users = this.userRepository.findAll();
        return users;
    }


}
