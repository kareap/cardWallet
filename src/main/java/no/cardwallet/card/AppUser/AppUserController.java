package no.cardwallet.card.AppUser;

import no.cardwallet.card.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class AppUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    final
    AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/signp")
    public String signUp(@ModelAttribute AppUser appUser) {
            return "signUp";
    }

    @PostMapping("/saveuser")
    public String validateUser(@ModelAttribute AppUser appUser, BindingResult bindingResult, @RequestParam String email, @RequestParam String password, @RequestParam String repeatPassword) {
        AppUserValidator appUserValidator = new AppUserValidator();
        appUser = new AppUser(email, password, repeatPassword);
        if (appUserValidator.supports(appUser.getClass())) {
            appUserValidator.validate(appUser, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            return "signUp";
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        return "login";//send to log in page
    }

    @GetMapping("/login")
    public String userLogin(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }


    @GetMapping("/settings")
    public String userSettings(@ModelAttribute AppUser appUser) {
        return "userSettings";
    }



    @GetMapping("/changeemail")
    public String changeEmail() {

        return "changeEmail";
    }


    @GetMapping("/changepassword")
    public String changePassword() {

        return "changePassword";
    }

    @GetMapping("/termsandconditions")
    public String termsAndConditions() {

        return "termsAndConditions";
    }

    @GetMapping("/deleteaccount")
    public String deleteAccount() {

        return "deleteAccount";
    }
}
