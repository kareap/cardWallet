package no.cardwallet.card.GiftCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class GiftCardController {

    @Autowired
    GiftCardRepository giftCardRepository;


    @GetMapping("/{id}")
    public String getAllCards(Model model, @PathVariable Long id) {
        List<GiftCard> giftCardList = (List<GiftCard>) giftCardRepository.findGiftCardByAppUserId(id);
        model.addAttribute("giftCardList", giftCardList);
        return "myCards";
    }

    @GetMapping("/addCard")
    public String addGiftCard() {
        return "addGiftCard";
    }

    @PostMapping("/addCard")
    public String saveGiftCard(@RequestParam String storeName, @RequestParam String cardCode, @RequestParam Double balance, Principal principal) {
        GiftCard giftCard = new GiftCard(storeName, cardCode, balance);

        giftCardRepository.save(giftCard);

//        String user = principal.getName();
//        userRepository.findByName;
//        return "redirect:/" + user.getId;
        return "redirect:/1";
    }


}
