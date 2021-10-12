package tr.com.foodstore.orderservice.model.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cardNo"})})
public class CreditCard extends BaseEntity {

    private Long cardNo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    private String ownerNameSurname;

    private Integer cvc;

    @OneToMany(targetEntity = Payment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "creditCard")
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User ownerUser;

    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getOwnerNameSurname() {
        return ownerNameSurname;
    }

    public void setOwnerNameSurname(String ownerNameSurname) {
        this.ownerNameSurname = ownerNameSurname;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
