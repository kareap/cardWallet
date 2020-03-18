package no.cardwallet.card.GiftCard;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class GiftCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardCode;
    private String storeName;
    private Date expiryDate;
    private Double balance;
    private String logoImage;
    private Long appUserId;

    public GiftCard(String storeName, String cardCode, Double balance, Date expiryDate) {
        this.storeName = storeName;
        this.cardCode = cardCode;
        this.balance = balance;
        this.expiryDate = expiryDate;
    }

    public GiftCard() {
    }


    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }
}
