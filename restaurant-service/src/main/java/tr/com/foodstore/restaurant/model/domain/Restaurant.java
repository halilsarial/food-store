package tr.com.foodstore.restaurant.model.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Restaurant extends BaseEntity {


    private String name;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_CHAIN_ID")
    private Restaurant restaurantChain;

    @OneToMany(targetEntity = Menu.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Menu> menus = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User ownerUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurantChain() {
        return restaurantChain;
    }

    public void setRestaurantChain(Restaurant restaurantChain) {
        this.restaurantChain = restaurantChain;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }
}
