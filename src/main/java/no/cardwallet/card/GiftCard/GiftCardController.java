package no.cardwallet.card.GiftCard;

import no.cardwallet.card.AppUser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
public class GiftCardController {

    @Autowired
    GiftCardRepository giftCardRepository;

    @Autowired
    AppUserRepository appUserRepository;

    //  Main page - Show all gift cards of user, by id
    @GetMapping("/myCards")
    public String getAllCards(Model model, Principal principal) {
        String email = principal.getName();
        Long appUserId = appUserRepository.findByEmail(email).getId();
        List<GiftCard> giftCardList = (List<GiftCard>) giftCardRepository.findGiftCardsByAppUserId(appUserId);
        model.addAttribute("giftCardList", giftCardList);
        return "myCards";
    }

    //  Show gift card details
    @GetMapping("/giftcard/{giftCardId}")
    public String showGiftCard(Model model, @PathVariable Long giftCardId) {
        GiftCard giftCard = giftCardRepository.findGiftCardById(giftCardId);
        model.addAttribute("giftCard", giftCard);
        return "showGiftCard";
    }

    //  Add gift card
    @GetMapping("/addgiftcard")
    public String addGiftCard() {
        return "addGiftCard";
    }

    //  Save gift card
    @PostMapping("/savegiftcard")
    public String saveGiftCard(@ModelAttribute GiftCard giftCard) {
        giftCardRepository.save(giftCard);
                return "redirect:/1";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        GiftCard giftCard = giftCardRepository.findById(id).get();
        model.addAttribute(giftCard);
        return "editGiftCard";
    }

/* @PostMapping("/save")
    public String saveGiftCard(@RequestParam String storeName, @RequestParam String cardCode, @RequestParam Double balance, Principal principal) {
        GiftCard giftCard = new GiftCard(storeName, cardCode, balance);
        giftCardRepository.save(giftCard);

//        Date newExpDate = expiryDate;
//        String username = principal.getName();
//        appUserRepository.fin
//        return "redirect:/" + user.getId;

        return "redirect:/1";
    }*/

    /*@PostMapping("/edit/{id}")
    public String editGiftCard(@RequestParam String storeName, @RequestParam String cardCode, @RequestParam Double balance, @RequestParam Long id) {
        GiftCard giftCard = giftCardRepository.findById(id).get();
        giftCard.setStoreName(storeName);
        giftCard.setCardCode(cardCode);
        giftCard.setBalance(balance);
        giftCardRepository.save(giftCard);
        return "redirect:/1";
    }*/

}
