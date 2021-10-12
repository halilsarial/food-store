package tr.com.foodstore.paymentservice.model.dto;

public class PaymentDto extends AbstractServiceDto{

    private Long cardNo;

    private Double totalAmount;

    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
