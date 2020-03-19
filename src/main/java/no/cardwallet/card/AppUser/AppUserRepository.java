package no.cardwallet.card.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;


public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByEmail(String email);
}
