package tr.com.foodstore.userservice.model.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})})
public class User extends BaseEntity {

    private String name;

    private String surname;

    private String userName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(targetEntity = CreditCard.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ownerUser")
    private Set<CreditCard> creditCards;

    @OneToMany(targetEntity = ConfirmationToken.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ConfirmationToken> confirmationTokens;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public Set<ConfirmationToken> getConfirmationTokens() {
        return confirmationTokens;
    }

    public void setConfirmationTokens(Set<ConfirmationToken> confirmationTokens) {
        this.confirmationTokens = confirmationTokens;
    }
}
