package com.delibot.userservice.web.models;

import com.delibot.userservice.util.UserType;

import java.io.Serializable;

public class UserAuthority implements Serializable {

    private Long typeId;

    private Long userId;

    private UserType userType;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        return this.userType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
            return (obj instanceof UserAuthority) && ((UserAuthority) obj).getUserType().equals(this.userType);
        }
}
