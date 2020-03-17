package no.cardwallet.card.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppUserController {

    @GetMapping("/signup")
    public String signUp(Model model) {
        AppUser appUser = new AppUser();
        String repeatPassword  = "";
        model.addAttribute("appUser", appUser);
        model.addAttribute("repeatPassword", repeatPassword);
        return "signUp";}

    @PostMapping("/saveuser")
    public String saveUser(){return null;} //send to log in page
}
