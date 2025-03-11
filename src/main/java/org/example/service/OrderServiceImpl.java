package org.example.service;

import org.example.Dto.OrderDto;
import org.example.config.HibernateConfig;
import org.example.converter.OrderConverter;
import org.example.dao.OrderDAO;
import org.example.dao.OrderDAOImpl;
import org.example.dao.ProductDAO;
import org.example.dao.ProductDAOImpl;
import org.example.entity.Order;
import org.example.entity.OrderItems;
import org.example.entity.Product;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final SessionFactory sessionFactory = HibernateConfig.geSessionFactory();
    private final ProductDAO productDAO = new ProductDAOImpl(sessionFactory);
    private final OrderDAO orderDAO = new OrderDAOImpl(sessionFactory);

    @Override
    public Order create(OrderDto orderDto) {
        List<Long> productIds = orderDto.getOrderItemsDtoList().stream().map(orderItemDto -> orderItemDto.getProductId()).collect(Collectors.toList());

        List<Product> products = productDAO.findAllById(productIds);

        HashMap<Long, Double> productPrices = products.stream()
                .collect(Collectors.toMap(Product::getId, Product::getPrice,
                        (price1, price2) -> price1, HashMap::new));

        Order order = OrderConverter.convertOrderToEntity(orderDto);

        order.getOrderItemsList().forEach(orderItem -> orderItem.setPrice(productPrices.get(orderItem.getId())));

        double total =0.0;
        for (OrderItems items: order.getOrderItemsList()){
            total += items.getPrice() * items.getQuantity();

        }
        order.setTotal(total);
        orderDAO.create(order);
        return order;
    }



}
