package com.delibot.orderservice.services;

import com.delibot.orderservice.datasource.entities.*;
import com.delibot.orderservice.datasource.repositories.DeliveryExecutiveStatusRepository;
import com.delibot.orderservice.datasource.repositories.FeedBackBasedRankingRepository;
import com.delibot.orderservice.datasource.repositories.OrderRepository;
import com.delibot.orderservice.web.models.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final FeedBackBasedRankingRepository feedBackBasedRankingRepository;

    private final DeliveryExecutiveStatusRepository deliveryExecutiveStatusRepository;


    @Autowired
    public OderServiceImpl(OrderRepository orderRepository, FeedBackBasedRankingRepository feedBackBasedRankingRepository, DeliveryExecutiveStatusRepository deliveryExecutiveStatusRepository){
        this.orderRepository=orderRepository;
        this.feedBackBasedRankingRepository = feedBackBasedRankingRepository;
        this.deliveryExecutiveStatusRepository = deliveryExecutiveStatusRepository;
    }

    private final Function<OrderEntity, Order> entityToOrder = orderEntity -> {
        Order order = new Order();
        order.setOrderId(orderEntity.getOrderId());
        order.setOrderState(orderEntity.getOrderState());
        order.setDelivererID(orderEntity.getDelivererID());
        order.setDeliveryLocation(orderEntity.getDeliveryLocation());
        order.setItemId(orderEntity.getItemId());
        order.setStoreId(orderEntity.getStoreId());
        order.setUserId(orderEntity.getUserId());
        return order;
    };

    private final Function<Order,OrderEntity> orderToEntity = order->{
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setOrderState(order.getOrderState());
        orderEntity.setDeliveryLocation(order.getDeliveryLocation());
        orderEntity.setDelivererID(order.getDelivererID());
        orderEntity.setItemId(order.getItemId());
        orderEntity.setStoreId(order.getStoreId());
        orderEntity.setUserId(order.getUserId());
        return orderEntity;
    };

    private Optional<FeedBackBasedRankingEntity> updateDeliveryExecutiveStatusAndAssignOrderToThatExecutive(Order order, FeedBackBasedRankingEntity feedBackBasedRankingEntity) {
        DeliveryExecutiveStatusEntity deliveryExecutiveStatusEntity = new DeliveryExecutiveStatusEntity();
        deliveryExecutiveStatusEntity.setUserId(feedBackBasedRankingEntity.getUserId());
        deliveryExecutiveStatusEntity.setDeliveryExecutiveState(DeliveryExecutiveState.WORKING_DELIVERING);
        deliveryExecutiveStatusEntity.setUpdatedLocation(order.getDeliveryLocation());
        deliveryExecutiveStatusRepository.save(deliveryExecutiveStatusEntity);
        order.setDelivererID(feedBackBasedRankingEntity.getUserId());
        order.setOrderState(OrderState.ON_THE_WAY);
        return Optional.of(feedBackBasedRankingEntity);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository
                .findById(orderId)
                .map(orderEntity ->Optional
                        .of(entityToOrder
                                .apply(orderEntity)))
                .orElseGet(Optional::empty);
    }


    @Override
    public Optional<Order> createOrder(Order order) {
        List<DeliveryExecutiveStatusEntity> byUpdatedLocationAndDeliveryExecutiveState = deliveryExecutiveStatusRepository
                .findByUpdatedLocationAndDeliveryExecutiveState(order.getDeliveryLocation(), DeliveryExecutiveState.WORKING_WAITING_FOR_ORDER);
        feedBackBasedRankingRepository
                .findTop1ByUserIdInOrderByAverageRatingDesc(
                        byUpdatedLocationAndDeliveryExecutiveState.stream()
                                .map(DeliveryExecutiveStatusEntity::getUserId)
                                .collect(Collectors.toList())
                ).map(feedBackBasedRankingEntity -> updateDeliveryExecutiveStatusAndAssignOrderToThatExecutive(order, feedBackBasedRankingEntity))
                .orElseGet(() -> {
                    if(!byUpdatedLocationAndDeliveryExecutiveState.isEmpty()){
                        //when there is no ranking for delivery executives
                        int size = byUpdatedLocationAndDeliveryExecutiveState.size();
                        int randomNumber=0;
                        if(size>1) {
                            randomNumber = new Random().ints(0, size -1).findFirst()
                                    .getAsInt();
                        }
                        DeliveryExecutiveStatusEntity deliveryExecutiveStatusEntity = byUpdatedLocationAndDeliveryExecutiveState.get(randomNumber);
                        FeedBackBasedRankingEntity feedBackBasedRankingEntity = new FeedBackBasedRankingEntity();
                        feedBackBasedRankingEntity.setUserId(deliveryExecutiveStatusEntity.getUserId());
                        updateDeliveryExecutiveStatusAndAssignOrderToThatExecutive(order, feedBackBasedRankingEntity);
                        return Optional.of(feedBackBasedRankingEntity);
                    }
                    else{
                        //No one to deliver the item
                        order.setOrderState(OrderState.CANCELLED);
                        return Optional.empty();
                    }
                });

        return Optional.ofNullable(entityToOrder
                .apply(orderRepository
                        .save(orderToEntity
                                .apply(order))));

    }

    @Override
    @Transactional
    public Optional<Order> updateOrder(Order order) {
        deliveryExecutiveStatusRepository.findById(order.getDelivererID())
                .map(deliveryExecutiveStatusEntity1 -> {
                    deliveryExecutiveStatusEntity1.setDeliveryExecutiveState(DeliveryExecutiveState.WORKING_WAITING_FOR_ORDER);
                    return Optional.of(deliveryExecutiveStatusRepository.save(deliveryExecutiveStatusEntity1));
                }).orElseGet(() -> {
                    log.error("Unable to update the delivery executive status");
                    return Optional.empty();
                });
        return Optional.of(entityToOrder.apply(orderRepository
                .save(orderToEntity.apply(order))));
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        return orderRepository
                .findByUserId(userId).stream()
                .map(entityToOrder::apply).collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrderByDeliveryExecutiveId(Long userId) {
        return orderRepository.findByDelivererID(userId).stream()
                .map(entityToOrder::apply).collect(Collectors.toList());

    }
}
