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
    private List<GiftCard> getGiftCards(Principal principal) {
        Long appUserId = getAppUserId(principal);
        return giftCardRepository.findGiftCardsByAppUserId(appUserId);
    }

    //  Main page - Show all gift cards of user, by id
    @GetMapping("/myCards")
    public String getAllCards(Model model, Principal principal) {
        List<GiftCard> giftCardList = getGiftCards(principal);
        model.addAttribute("giftCardList", giftCardList);
        return "myCards";
    }

    //  Show gift card details
    @GetMapping("/giftcard/{appUserId}/{giftCardId}")
    public String showGiftCard(Model model, @PathVariable Long appUserId, @PathVariable Long giftCardId) {
        GiftCard giftCard = giftCardRepository.findGiftCardById(giftCardId);
        model.addAttribute("giftCard", giftCard);
        model.addAttribute("appUserId", appUserId);
        return "showGiftCard";
    }

    //  Add gift card
    @GetMapping("/addgiftcard")
    public String addGiftCard() {
        return "addGiftCard";
    }

    //  Save gift card
    @PostMapping("/savegiftcard/{cardIdPath}")
    public String saveGiftCard(@ModelAttribute GiftCard giftCard, @PathVariable Long cardIdPath, Principal principal) {
        Long appUserId = getAppUserId(principal);

        if (cardIdPath != null){
            giftCard.setId(cardIdPath);
        }
        giftCard.setAppUserId(appUserId);
        giftCardRepository.save(giftCard);
        return "redirect:/myCards";
    }

    @GetMapping("/edit/{appUserId}/{cardId}")
    public String edit(Model model, @PathVariable Long appUserId, @PathVariable Long cardId, Principal principal) {
        Long principleUserId = getAppUserId(principal);

        List<GiftCard> giftCardList = giftCardRepository.findGiftCardsByAppUserId(principleUserId);

        if (!giftCardList.contains(giftCardRepository.findGiftCardById(cardId)) || !principleUserId.equals(appUserId)) {
            return "defaultView";
        }

        GiftCard giftCard = giftCardRepository.findById(cardId).get();
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
