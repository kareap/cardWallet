package no.cardwallet.card.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/signup")
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

    @DeleteMapping("/delete")
    public String deleteUser(Principal principal) {
        String email = principal.getName();
       /* Long id = appUserRepository.findByEmail(email).getId();
        appUserRepository.deleteById(id);*/
        appUserRepository.deleteByEmail(email);
        return "redirect:/signup";
    }


}
