package com.delibot.userservice.services;

import com.delibot.userservice.web.models.DeliveryExecutiveStatus;
import com.delibot.userservice.web.models.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public Optional<User> getUserById(Long userId);

    public List<User> getAllUsers();

    public Optional<User> saveUser(User user);

    @Transactional
    Optional<User> updateUser(User user);

    Optional<DeliveryExecutiveStatus> saveDeliverySExecutiveStatus(DeliveryExecutiveStatus deliveryExecutiveStatus);

}
