package org.example.Dto;

import org.example.entity.PaymentStatusEnum;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long clientId;
    private PaymentStatusEnum paymentStatus;

    List<OrderItemsDto> orderItemsDtoList = new ArrayList<>();

    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;

    }

    public List<OrderItemsDto> getOrderItemsDtoList() {
        return orderItemsDtoList;
    }
    public void setOrderItemsDtoList(List<OrderItemsDto> orderItemsDtoList) {
        this.orderItemsDtoList = orderItemsDtoList;
    }

}
