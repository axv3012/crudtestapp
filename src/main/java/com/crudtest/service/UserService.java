package com.crudtest.service;

import com.crudtest.model.User;

import java.util.List;


public interface UserService {

    User getUserByLastName(String lastName);
    void saveUser(User user);
    List<User> findAll();
}
