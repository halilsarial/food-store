package tr.com.foodstore.restaurant.model.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})})
public class User extends BaseEntity {

    private String userName;

    @OneToMany(targetEntity = Restaurant.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ownerUser")
    private Set<Restaurant> restaurants;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
