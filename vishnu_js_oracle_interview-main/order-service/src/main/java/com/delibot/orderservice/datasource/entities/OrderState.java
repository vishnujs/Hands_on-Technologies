package com.delibot.orderservice.datasource.entities;

public enum OrderState {
    CART("CART"),
    ORDERED("ORDERED"),
    ON_THE_WAY("ON_THE_WAY"),
    DELIVERED("DELIVERED"),
    CANCELLED("CANCELLED");

    private String orderStatus;

    OrderState(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
