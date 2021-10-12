package tr.com.foodstore.orderservice.model.domain;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ORDER_ID"})})
public class Payment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    private CreditCard creditCard;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
