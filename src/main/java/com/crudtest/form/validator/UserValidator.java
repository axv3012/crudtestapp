package com.crudtest.form.validator;

import com.crudtest.form.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;


@Component
public class UserValidator implements Validator {

    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {


        UserForm userForm = (UserForm) o;
        if (userForm.getEmail() != null && !pattern.matcher(userForm.getEmail()).matches()) {
            errors.rejectValue("email", "email.invalid", "Please enter a valid email address");
        }
        if (!userForm.getEmail().equals(userForm.getRepeatedEmail())) {
            errors.rejectValue("repeatedEmail", "email.repeated", "Please enter same email.");
        }

    }

}
