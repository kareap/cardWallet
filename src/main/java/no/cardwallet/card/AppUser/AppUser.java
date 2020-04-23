package no.cardwallet.card.AppUser;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Transient
    private String repeatPassword;

    public AppUser() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    //used by @ModelAttribute
    public void setRepeatPassword(String repeatPassword) { this.repeatPassword = repeatPassword; }
}
