package no.cardwallet.card.AppUser;

import javax.persistence.*;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Transient //spring hopper over variabelen når du lager tabell
    private String repeatEmail;
    private String password;
    @Transient
    private String repeatPassword;


    public AppUser() {}

    public AppUser(String email, String password, String repeatPassword) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    //trenger vi denne controller??
    public AppUser(String email, String repeatEmail, String password, String repeatPassword) {
        this.email = email;
        this.repeatEmail = repeatEmail;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }


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

    public String getRepeatEmail() {
        return repeatEmail;
    }

    public void setRepeatEmail(String repeatEmail) {
        this.repeatEmail = repeatEmail;
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

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
