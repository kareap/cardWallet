package no.cardwallet.card.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;


@Controller
public class AppUserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    final
    AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/signUp")
    public String signUp(@ModelAttribute AppUser appUser) {
        return "signUp";
    }

    @PostMapping("/saveUser")
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
    public String userLogin() {
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

    @GetMapping("/changeEmail")
    public String changeEmail(Model model, Principal principal) {
        String email = principal.getName();
        AppUser appUser = appUserRepository.findByEmail(email);
        model.addAttribute(appUser);
        return "changeEmail";
    }

    @PostMapping("/saveChangedEmail")
    public String saveChangedEmail(Model model, Principal principal, @ModelAttribute AppUser appUserPosting) {
        String email = principal.getName();
        AppUser appUser = appUserRepository.findByEmail(email);
        appUser.setEmail(appUserPosting.getEmail());
        appUserRepository.save(appUser);
        model.addAttribute(appUser);
        return "SuccessfullyChangedEmail";
    }

    @GetMapping("/changepassword")
    public String changePassword() {

        return "changePassword";
    }

    @GetMapping("/termsandconditions")
    public String termsAndConditions() {

        return "termsAndConditions";
    }

    @Transactional
    @GetMapping("/deleteAppUser")
    public String deleteAppUser(Principal principal) {
        String email = principal.getName();
        appUserRepository.deleteAppUserByEmail(email);
        return "redirect:/signUp";
    }
}
