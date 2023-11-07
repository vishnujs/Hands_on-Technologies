package com.delibot.orderservice.services;

import com.delibot.orderservice.web.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    public Optional<Order> getOrderById(Long orderId);

    public Optional<Order> createOrder(Order order);

    public Optional<Order> updateOrder(Order order);

    List<Order> getOrderByUserId(Long userId);

    List<Order> getOrderByDeliveryExecutiveId(Long userId);


}
