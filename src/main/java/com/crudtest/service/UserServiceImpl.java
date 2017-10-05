package com.crudtest.service;

import com.crudtest.model.User;
import com.crudtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    List<User> users = new ArrayList<User>();
    @Override
    public User getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

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
