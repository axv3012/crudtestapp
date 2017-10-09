package com.crudtest.service.user;

import com.crudtest.model.User;

import java.util.List;


public interface UserService {

    void saveUser(User user);
    List<User> findAll();
}
