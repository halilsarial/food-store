package tr.com.foodstore.userservice.model.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class ConfirmationToken extends BaseEntity {

    @Column(name = "confirmation_token")
    private String confirmationToken;

    private Boolean isActive = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
