package com.delibot.userservice.datasource.entities;

import com.delibot.userservice.util.UserType;

import javax.persistence.*;

@Entity
@Table(name="USER_AUTHORITY")
public class UserAuthorityEntity {

    @Id
    @Column(name="TYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="USER_TYPE")
    @Enumerated(EnumType.STRING)
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
    public boolean equals(Object obj) {
        return (obj instanceof UserAuthorityEntity) && ((UserAuthorityEntity) obj).getUserType().equals(this.userType);
    }

    @Override
    public int hashCode() {
        return this.userType.hashCode();
    }
}
