package com.delibot.userservice.datasource.entities;

import javax.persistence.*;

@Entity
@Table(name= "DELIVERY_EXECUTIVE_STATUS")
public class DeliveryExecutiveStatusEntity {

    @Id
    @Column(name="STATUS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;


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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
