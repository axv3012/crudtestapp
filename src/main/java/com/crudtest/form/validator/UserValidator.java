package com.crudtest.form.validator;

import com.crudtest.form.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
        validateEmail(userForm, errors);
        validatePassword(userForm, errors);

    }

    public void validateEmail(UserForm userForm, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.form",
                "Please don't leave email empty or with whitespaces");

        if (userForm.getEmail() != null && !pattern.matcher(userForm.getEmail()).matches()) {
            errors.rejectValue("email", "email.invalid", "Please enter a valid email address");
        }

        if (!userForm.getEmail().equals(userForm.getRepeatedEmail())) {
            errors.rejectValue("repeatedEmail", "email.repeated", "Please enter the same email as above.");
        }
    }

    public void validatePassword(UserForm userForm, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "password", "password.invalid", "Please input an password");

        if (userForm.getPassword() == null) {
            errors.rejectValue("password", "password.invalid", "Please enter a valid password");
        }
        if (!userForm.getPassword().equals(userForm.getRepeatedPassword())) {
            errors.rejectValue("password", "password.repeated", "Please enter the same password");
        }
    }

}
