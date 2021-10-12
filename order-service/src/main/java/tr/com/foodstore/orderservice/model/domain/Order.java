package tr.com.foodstore.orderservice.model.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FS_ORDER")
public class Order extends BaseEntity {

    @ManyToMany(targetEntity = Food.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_FOODS", joinColumns = {@JoinColumn(name = "ORDER_ID")}, inverseJoinColumns = {@JoinColumn(name = "FOOD_ID")})
    private Set<Food> foods = new HashSet<>();

    @OneToOne(targetEntity = Payment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }
}
