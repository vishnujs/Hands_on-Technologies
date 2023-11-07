package com.delibot.userservice.util;

public enum UserType {
    STORE_OWNER("STORE_OWNER"),
    DELIVERY_EXECUTIVE("DELIVERY_EXECUTIVE"),
    CUSTOMER("CUSTOMER");

    private String uerType;

    UserType(String uerType) {
        this.uerType = uerType;
    }
}
