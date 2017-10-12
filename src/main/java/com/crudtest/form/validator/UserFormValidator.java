package com.crudtest.form.validator;

import com.crudtest.form.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
