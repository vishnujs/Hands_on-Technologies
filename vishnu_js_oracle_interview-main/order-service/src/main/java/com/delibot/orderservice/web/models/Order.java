package com.delibot.orderservice.web.models;

import com.delibot.orderservice.datasource.entities.OrderState;

import java.io.Serializable;


public class Order implements Serializable {

    private Long orderId;

    private Long userId;

    private Long delivererID;

    private OrderState orderState;

    private String deliveryLocation;

    private Long storeId;

    private Long itemId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDelivererID() {
        return delivererID;
    }

    public void setDelivererID(Long delivererID) {
        this.delivererID = delivererID;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
