package org.example.service;

import org.example.Dto.OrderDto;
import org.example.entity.Order;

public interface OrderService {

    Order create(OrderDto orderDto);


}
