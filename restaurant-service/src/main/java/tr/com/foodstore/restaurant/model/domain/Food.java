package tr.com.foodstore.restaurant.model.domain;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "menu_id"})})
public class Food extends BaseEntity {

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
