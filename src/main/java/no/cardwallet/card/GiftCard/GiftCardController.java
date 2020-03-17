package no.cardwallet.card.GiftCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
