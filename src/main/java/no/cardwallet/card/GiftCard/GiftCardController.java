package no.cardwallet.card.GiftCard;

import no.cardwallet.card.AppUser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class GiftCardController {

    @Autowired
    GiftCardRepository giftCardRepository;

    @Autowired
    AppUserRepository appUserRepository;

    //part of refactoring
    private Long getAppUserId(Principal principal) {
        String email = principal.getName();
        return appUserRepository.findByEmail(email).getId();
    }

    //part of refactoring
    private List<GiftCard> getAllCards(Principal principal) {
        Long appUserId = getAppUserId(principal);
        return giftCardRepository.findGiftCardsByAppUserId(appUserId);
    }

    //  Main page - Show all gift cards of user, by id
    @GetMapping("/myCards")
    public String getAllCards(Model model, Principal principal) {
        List<GiftCard> giftCardList = getAllCards(principal);
        model.addAttribute("giftCardList", giftCardList);
        return "myCards";
    }

    //  Show gift card details
    @GetMapping("/showGiftCard/{appUserId}/{giftCardId}")
    public String showGiftCard(Model model, @PathVariable Long appUserId, @PathVariable Long giftCardId) {
        GiftCard giftCard = giftCardRepository.findGiftCardById(giftCardId);

        model.addAttribute("giftCard", giftCard);
        model.addAttribute("appUserId", appUserId);

//        if (giftCard.getBalanceInt() == null){
//            return "defaultView";
//        }

        return "showGiftCard";
    }

    //  Add gift card
    @GetMapping("/addGiftCard")
    public String addGiftCard(Model model, Principal principal) {
        Long appUserId = getAppUserId(principal);
        model.addAttribute("appUserId", appUserId);
        model.addAttribute("giftCard", new GiftCard());
        return "addGiftCard";
    }


    //  Save new gift card
    @PostMapping("/saveNewGiftCard/")
    public String saveNewGiftCard(@ModelAttribute GiftCard giftCard, Principal principal) {
        Long appUserId = getAppUserId(principal);

        giftCard.setAppUserId(appUserId);
        giftCardRepository.save(giftCard);

        return "redirect:/myCards";
    }

    @GetMapping("/editGiftCard/{appUserId}/{cardId}")
    public String editGiftCard(Model model, @PathVariable Long appUserId, @PathVariable Long cardId, Principal principal) {
        Long principleUserId = getAppUserId(principal);

        GiftCard tempGiftCard = new GiftCard();
        model.addAttribute("tempGiftCard", tempGiftCard);

        List<GiftCard> giftCardList = giftCardRepository.findGiftCardsByAppUserId(principleUserId);

        if (!giftCardList.contains(giftCardRepository.findGiftCardById(cardId)) || !principleUserId.equals(appUserId)) {
            return "defaultView";
        }
        GiftCard giftCard = giftCardRepository.findById(cardId).get();
        model.addAttribute(giftCard);
        return "editGiftCard";
    }


    @PostMapping("/saveEditedGiftCard/{appUserId}/{cardId}")
    public String savEditedGiftCard(@ModelAttribute GiftCard giftCard, @ModelAttribute GiftCard tempGiftCard, @PathVariable Long cardId, Principal principal) {
        Long appUserId = getAppUserId(principal);

//        GiftCard tempGiftCard = new GiftCard();
//        model.addAttribute("tempGiftCard", tempGiftCard);

        if (cardId != null) {
            giftCard.setId(cardId);
        }
        giftCard.setAppUserId(appUserId);
        giftCardRepository.save(giftCard);

        giftCard.setBalanceInt(tempGiftCard.getBalanceInt());

        return "redirect:/myCards";
    }
}