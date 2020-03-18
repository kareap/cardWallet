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
    @GetMapping("/{appUserId}")
    public String getAllCards(Model model, @PathVariable Long appUserId) {
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
    public String saveGiftCard(@RequestParam String storeName, @RequestParam String cardCode, @RequestParam Double balance, @RequestParam Date expiryDate, Principal principal) {

        GiftCard giftCard = new GiftCard(storeName, cardCode, balance, expiryDate);

        giftCardRepository.save(giftCard);

//        Date newExpDate = expiryDate;
//        String username = principal.getName();
//        appUserRepository.fin
//        return "redirect:/" + user.getId;

        return "redirect:/1";
    }


}
