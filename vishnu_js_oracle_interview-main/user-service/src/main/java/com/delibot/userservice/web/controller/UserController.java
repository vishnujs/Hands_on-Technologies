package com.delibot.userservice.web.controller;

import com.delibot.userservice.services.UserServiceImpl;
import com.delibot.userservice.web.models.DeliveryExecutiveStatus;
import com.delibot.userservice.web.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    public static final String BASE_URL = "/api/v1/user";


    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id")long userId){
        log.debug("Request to controller for getting user based on user id initiated");
        return userService.getUserById(userId).map(ResponseEntity::ok)
                .orElseGet(()-> new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        log.debug("Request to controller for getting all user initiated");
        return userService.getAllUsers();
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        log.debug("Request to controller for creating user initiated for user:{}",user.toString());
        return userService.saveUser(user).map(ResponseEntity::ok)
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        log.debug("Request to controller for updating user initiated for user:{}",user.toString());
        return userService.updateUser(user).map(ResponseEntity::ok)
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/saveOrUpdateDeliveryExecutiveState")
    public ResponseEntity updateDeliveryState(@RequestBody DeliveryExecutiveStatus deliveryExecutiveStatus){
        log.debug("Request to controller for updating delivery executive initiated for deliveryExecutiveStatus:{}"
                ,deliveryExecutiveStatus.toString());
        return userService.saveDeliverySExecutiveStatus(deliveryExecutiveStatus).map(ResponseEntity::ok)
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/deliveryExecutiveState/{id}")
    public ResponseEntity<DeliveryExecutiveStatus> getDeliveryExecutiveState(@PathVariable("id")long userId){
        log.debug("Request to controller for getting user based on user id initiated");
        return userService.getDeliverExecutiveState(userId).map(ResponseEntity::ok)
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
