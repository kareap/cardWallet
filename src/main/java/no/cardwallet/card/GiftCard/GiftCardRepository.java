package no.cardwallet.card.GiftCard;

import no.cardwallet.card.AppUser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GiftCardRepository extends CrudRepository<GiftCard, Long>{

    List<GiftCard> findGiftCardByAppUserId(Long id);

    GiftCard findGiftCardById(Long id);
}
