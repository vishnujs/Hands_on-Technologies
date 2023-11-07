package com.delibot.userservice.web.models;

import com.delibot.userservice.datasource.entities.DeliveryExecutiveState;


public class DeliveryExecutiveStatus {

    private Long statusId;

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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "DeliveryExecutiveStatus{" +
                "statusId=" + statusId +
                ", userId=" + userId +
                ", updatedLocation='" + updatedLocation + '\'' +
                ", deliveryExecutiveState=" + deliveryExecutiveState +
                '}';
    }
}
