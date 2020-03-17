package no.cardwallet.card.AppUser;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AppUserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AppUser.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        AppUser appUser = (AppUser) object;
        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty", "Please enter a valid email address.");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty", "Please enter a valid password.");

        if (!appUser.getPassword().equals(appUser.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "password.notEqual", "Passwords not equal.");
        }
    }
}
