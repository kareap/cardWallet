package no.cardwallet.card.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

<<<<<<< HEAD
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
=======
import org.springframework.beans.factory.annotation.Autowired;

public class AppUserController {


>>>>>>> 6cc051b5e4832d539d826d236fc22bd8dc38707f
}
