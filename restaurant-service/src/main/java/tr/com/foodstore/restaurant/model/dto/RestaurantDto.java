package tr.com.foodstore.restaurant.model.dto;

import javax.validation.constraints.Size;

public class RestaurantDto extends AbstractServiceDto {
    private Long id;
    @Size(min = 2)
    private String name;

    private String ownerUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }
}
