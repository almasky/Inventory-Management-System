package org.example.client;
import org.example.Dto.OrderItemsDto;
import org.example.Dto.OrderDto;
import org.example.entity.Order;
import org.example.entity.OrderItems;
import org.example.entity.PaymentStatusEnum;
import org.example.service.OrderService;
import org.example.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Write the details of your order");

        //collect client id
        System.out.println("Please enter client id");
        Long clientId = sc.nextLong();

        // Collect payment status
        System.out.println("Please enter payment status");
        String paymentStatus = sc.next();

        // Validate payment status input
        PaymentStatusEnum paymentStatusEnum;
        try {
            paymentStatusEnum = PaymentStatusEnum.valueOf(paymentStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid payment status. Please enter a valid status (e.g., PAID, UNPAID).");
            sc.close();
            return;
        }
        // Collect order items

        List<OrderItems> orderItemsList = new ArrayList<>();
        int c;
        do {
            System.out.println("Please write product id ");
            Long productId = sc.nextLong();

            System.out.println("Please enter quantity ");
            Integer quantity = sc.nextInt();

            OrderItems orderItems = new OrderItems(productId, quantity);
            orderItemsList.add(orderItems);

            System.out.println("Press 1 to continue adding products, or 0 if your product list is complete.");
            c = sc.nextInt();
        } while (c != 0);

        // Create and process the order
        Order order = new Order();
        order.setClientId(clientId);
        order.setOrderItemstList(orderItemsList);
        order.setPaymentStatus(paymentStatusEnum);

        // Map to OrderDto
        OrderDto orderDto = new OrderDto();
        orderDto.setClientId(order.getClientId());
        orderDto.setPaymentStatus(order.getPaymentStatus());

        // Null-check and map order items to DTOs
        if (order.getOrderItemstList() != null) {
            orderDto.setOrderItemsDtoList(order.getOrderItemsList()
            .stream().map(orderItems -> new OrderItemsDto(orderItems.getProduct().getId(),orderItems.getQuantity()))
                    .collect(Collectors.toList()));
        }else{
            orderDto.setOrderItemsDtoList(new ArrayList<>()); // Set empty list if no items
        }

        // Call the service to create the order
        OrderService orderService = new OrderServiceImpl();
        orderService.create(orderDto);

        System.out.println("Order has been created successfully!");
        sc.close();

    }
}

