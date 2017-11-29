package com.crudtest.service.user.impl;

import com.crudtest.form.UserForm;
import com.crudtest.model.User;
import com.crudtest.repository.UserRepository;
import com.crudtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(UserForm userForm) {
        if (userRepository.findOneByEmail(userForm.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }
        User newUser = new User();
        newUser.setId(userForm.getId());
        newUser.setFirstName(userForm.getFirstName());
        newUser.setLastName(userForm.getLastName());
        newUser.setEmail(userForm.getEmail());
        newUser.setPasswordHash(new BCryptPasswordEncoder().encode(userForm.getPassword()));
        newUser.setRole(userForm.getRole());
        newUser.setDateCreated(new Date());
        newUser.setDateModified(new Date());
        newUser.setApproved(false);
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(UserForm userForm) {
        User updateUser = userRepository.findOne(userForm.getId());

        updateUser.setFirstName(userForm.getFirstName());
        updateUser.setLastName(userForm.getLastName());

        updateUser.setDateModified(new Date());
        return userRepository.save(updateUser);
    }


    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User adminApprove(UserForm userForm) {
        User approveUser = userRepository.findOne(userForm.getId());

        approveUser.setApproved(true);
        return userRepository.save(approveUser);
    }

    @Override
    public void deleteUser(UserForm userForm) {
        User deleteUser = new User();
        deleteUser.setId(userForm.getId());
        userRepository.delete(deleteUser);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }


}
