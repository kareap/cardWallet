package no.cardwallet.card.GiftCard;

import no.cardwallet.card.AppUser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        List<GiftCard> giftCardList = (List<GiftCard>) giftCardRepository.findGiftCardByAppUserId(appUserId);
        model.addAttribute("giftCardList", giftCardList);
        return "myCards";
    }


//  Show one-pager store gift card
    @GetMapping("/giftcard/{giftCardId}")
    public String showGiftCard(Model model, @PathVariable Long giftCardId) {
        GiftCard giftCard = giftCardRepository.findGiftCardById(giftCardId);
        model.addAttribute("giftCard", giftCard);
        return "showGiftCard";
    }

//  Add gift card
    @GetMapping("/addCard")
    public String addGiftCard() {
        return "addGiftCard";
    }

//  Save gift card
    @PostMapping("/addCard")
    public String saveGiftCard(@RequestParam String storeName, @RequestParam String cardCode, @RequestParam Double balance, @RequestParam Date expiryDate, Principal principal) {

//        Date newExpDate = expiryDate;
        GiftCard giftCard = new GiftCard(storeName, cardCode, balance, expiryDate);

        giftCardRepository.save(giftCard);

//        String username = principal.getName();
//        appUserRepository.fin
//        return "redirect:/" + user.getId;

        return "redirect:/1";
    }


}
