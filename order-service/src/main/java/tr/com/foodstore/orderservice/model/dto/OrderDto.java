package tr.com.foodstore.orderservice.model.dto;

import java.util.HashSet;
import java.util.Set;

public class OrderDto extends AbstractServiceDto{

    private Long cardNo;

    private Set<Long> foodIds = new HashSet<>();

    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public Set<Long> getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(Set<Long> foodIds) {
        this.foodIds = foodIds;
    }
}
