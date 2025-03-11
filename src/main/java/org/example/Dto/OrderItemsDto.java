package org.example.Dto;

public class OrderItemsDto {

    private Long productId;
    private Integer quantity;


    // Constructors
    public OrderItemsDto(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    //Getter Setter
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "OrderItemsDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
