package com.crudtest.service.user;

import com.crudtest.form.UserForm;
import com.crudtest.model.User;

import java.util.List;


public interface UserService {
    User findByEmail(String email);
    void saveUser(UserForm userForm);
    List<User> findAll();
}
