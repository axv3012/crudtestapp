package com.crudtest.service.user;

import com.crudtest.form.UserForm;
import com.crudtest.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> findUserById(Long id);

    Optional<User> findOneByEmail(String email);

    User saveUser(UserForm userForm);

    User updateUser(UserForm userForm);

    List<User> findAll();

    User adminApprove(UserForm userForm);

    void deleteUser(UserForm userForm);
}
