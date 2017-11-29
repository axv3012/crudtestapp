package com.crudtest.form;

import com.crudtest.model.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserForm {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;
    @NotEmpty
    private String lastName;

    private String email;

    private String repeatedEmail;

    private String password;

    private String repeatedPassword;

    private Role role = Role.USER;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepeatedEmail() {
        return repeatedEmail;
    }

    public void setRepeatedEmail(String repeatedEmail) {
        this.repeatedEmail = repeatedEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}

