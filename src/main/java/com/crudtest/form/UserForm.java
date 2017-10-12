package com.crudtest.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserForm {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String repeatedEmail;

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
}
