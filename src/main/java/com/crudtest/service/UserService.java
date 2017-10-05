package com.crudtest.service;

import com.crudtest.model.User;


public interface UserService {

    public User getUserByLastName(String lastName);
    public void saveUser(User user);
}
