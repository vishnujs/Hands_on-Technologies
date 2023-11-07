package com.delibot.orderservice.web.controller;

import com.delibot.orderservice.web.models.Order;
import com.delibot.orderservice.services.OrderService;
import com.delibot.orderservice.services.PublishingService;
import com.delibot.orderservice.web.models.FeedbackKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {


    private final OrderService orderService;

    private final PublishingService publishingService;

    @Autowired
    public OrderController(OrderService orderService, PublishingService publishingService) {
        this.orderService = orderService;
        this.publishingService = publishingService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        return orderService
                .getOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderService
                .createOrder(order)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<Order>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return orderService
                .updateOrder(order)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<Order>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/publishfeedback")
    public ResponseEntity publishFeedback(@RequestBody FeedbackKafka feedbackKafka) {
        publishingService.publish(feedbackKafka);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/userOrderHistory/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService
                .getOrderByUserId(userId);
    }

    @GetMapping("/deliveryOrderHistory/{deliveryExecutive}")
    public List<Order> getOrdersByDeliveryExecutiveId(@PathVariable Long deliveryExecutive) {
        return orderService
                .getOrderByDeliveryExecutiveId(deliveryExecutive);
    }
}