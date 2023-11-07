package com.delibot.orderservice.datasource.entities;

import javax.persistence.*;

@Entity
@Table(name= "DELIVERY_EXECUTIVE_STATUS")
public class DeliveryExecutiveStatusEntity {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "UPDATED_LOCATION")
    private String updatedLocation;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
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
