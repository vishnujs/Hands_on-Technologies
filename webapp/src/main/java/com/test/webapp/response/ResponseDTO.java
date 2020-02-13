package com.test.webapp.response;

public class ResponseDTO {

    private String userName;

    private String city;

    public ResponseDTO(String userName, String city) {
        this.userName = userName;
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
