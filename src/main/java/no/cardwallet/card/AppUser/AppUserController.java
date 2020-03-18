package no.cardwallet.card.AppUser;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class AppUserController {

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
        appUserRepository.save(appUser);
        return "defaultView";//send to log in page
    }

    @GetMapping("/login")
    public String userLogin(){
        return "login";
    }

    @PostMapping("/login/{id}")
    public String loggedInUser(@PathVariable Long id, @RequestParam String typedEmail, @ModelAttribute AppUser appUser){
        appUser = appUserRepository.findByEmail(typedEmail);
        if (appUser != null) {
            return null;
        }
        return null;
    }

    @GetMapping("/settings")
    public String userSettings(@ModelAttribute AppUser appUser) {
        return "userSettings";
    }


}
