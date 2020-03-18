package no.cardwallet.card;

import no.cardwallet.card.AppUser.AppUser;
import no.cardwallet.card.AppUser.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CardWalletService implements UserDetailsService {

    final
    AppUserRepository appUserRepository;

    public CardWalletService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = AppUserRepository.findByEmail();
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CardWalletService(appUser);
    }
}
