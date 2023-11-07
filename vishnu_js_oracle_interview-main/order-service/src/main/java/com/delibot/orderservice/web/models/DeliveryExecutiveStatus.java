package com.delibot.orderservice.web.models;


import com.delibot.orderservice.datasource.entities.DeliveryExecutiveState;

public class DeliveryExecutiveStatus {

    private Long userId;

    private String updatedLocation;

    private DeliveryExecutiveState deliveryExecutiveState;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUpdatedLocation() {
        return updatedLocation;
    }

    public void setUpdatedLocation(String updatedLocation) {
        this.updatedLocation = updatedLocation;
    }

    public DeliveryExecutiveState getDeliveryExecutiveState() {
        return deliveryExecutiveState;
    }

    public void setDeliveryExecutiveState(DeliveryExecutiveState deliveryExecutiveState) {
        this.deliveryExecutiveState = deliveryExecutiveState;
    }
}
