package com.crudtest.service.user;

import com.crudtest.model.User;

import java.util.List;


public interface UserService {

    User getUserByLastName(String lastName);
    void saveUser(User user);
    List<User> findAll();
}
