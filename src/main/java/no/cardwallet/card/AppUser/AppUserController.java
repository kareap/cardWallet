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

    @GetMapping("/sign-up")
    public String signUp(@ModelAttribute AppUser appUser) {
        return "signUp";
    }

    @PostMapping("/save-user")
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
        return "userLogin";//send to log in page
    }

    @GetMapping("/userLogin")
    public String userLogin() {
        return "userLogin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "userLogin";
    }

    @GetMapping("/settings")
    public String userSettings(@ModelAttribute AppUser appUser) {
        return "userSettings";
    }

    @GetMapping("/change-email")
    public String changeEmail(Model model, Principal principal) {
        String email = principal.getName();
        AppUser appUser = appUserRepository.findByEmail(email);
        model.addAttribute(appUser);
        return "changeEmail";
    }

    @PostMapping("/save-changed-email")
    public String saveChangedEmail(Model model, Principal principal, @ModelAttribute AppUser appUserPosting) {
        String email = principal.getName();
        AppUser appUser = appUserRepository.findByEmail(email);
        appUser.setEmail(appUserPosting.getEmail());
        appUserRepository.save(appUser);
        model.addAttribute(appUser);
        return "SuccessfullyChangedEmail";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model, Principal principal) {
        String email = principal.getName();
        AppUser appUser = appUserRepository.findByEmail(email);
        model.addAttribute(appUser);

        return "changePassword";
    }

    @PostMapping("/save-changed-password")
    public String saveChangedPassword(Model model, Principal principal, @ModelAttribute AppUser appUserPosting) {
        String email = principal.getName();
        AppUser appUser = appUserRepository.findByEmail(email);
        appUser.setPassword(appUserPosting.getPassword());
        appUserRepository.save(appUser);
        model.addAttribute(appUser);

        return "successfullyChangedPassword";
    }

    @GetMapping("/terms-and-conditions")
    public String termsAndConditions() {

        return "termsAndConditions";
    }

    @Transactional
    @GetMapping("/delete-app-user")
    public String deleteAppUser(Principal principal) {
        String email = principal.getName(); //cannot delete user before the user's cards have been deleted
        appUserRepository.deleteAppUserByEmail(email);
        return "redirect:/sign-up";
    }
}
