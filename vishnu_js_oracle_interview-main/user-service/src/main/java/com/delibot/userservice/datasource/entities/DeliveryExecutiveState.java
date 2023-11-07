package com.delibot.userservice.datasource.entities;

public enum DeliveryExecutiveState {
    WORKING_WAITING_FOR_ORDER("WORKING_WAITING_FOR_ORDER"),
    WORKING_DELIVERING("WORKING_DELIVERING"),
    NOT_WORKING("NOT_WORKING");


    private String state;

    DeliveryExecutiveState(String state) {
        this.state = state;
    }
}
