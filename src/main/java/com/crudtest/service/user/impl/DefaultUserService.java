package com.crudtest.service.user.impl;

import com.crudtest.model.User;
import com.crudtest.repository.UserRepository;
import com.crudtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void saveUser(User user) {
        if(user.getDateCreated() == null){
            user.setDateCreated(new Date());
        }
        user.setDateModified(new Date());
        userRepository.save(user);
    }


    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }




}
