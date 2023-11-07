package com.delibot.orderservice.datasource.entities;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_DATA")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID" )
    private Long orderId;

    @Column(name = "USER_ID" )
    private Long userId;

    @Column(name = "DELIVERER_ID" )
    private Long delivererID;

    @Column(name = "ORDER_STATE" )
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(name = "DELIVERY_LOCATION" )
    private String deliveryLocation;

    @Column(name = "STORE_ID" )
    private Long storeId;

    @Column(name = "ITEM_ID" )
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
