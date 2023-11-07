package com.delibot.userservice.services;

import com.delibot.userservice.datasource.entities.DeliveryExecutiveState;
import com.delibot.userservice.datasource.entities.DeliveryExecutiveStatusEntity;
import com.delibot.userservice.datasource.entities.UserAuthorityEntity;
import com.delibot.userservice.datasource.entities.UserEntity;
import com.delibot.userservice.datasource.repositories.DeliveryExecutiveStatusRepository;
import com.delibot.userservice.datasource.repositories.UserAuthorityRepository;
import com.delibot.userservice.datasource.repositories.UserRepository;
import com.delibot.userservice.web.models.DeliveryExecutiveStatus;
import com.delibot.userservice.web.models.User;
import com.delibot.userservice.web.models.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserAuthorityRepository userAuthorityRepository;

    private final DeliveryExecutiveStatusRepository deliveryExecutiveStatusRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository, UserAuthorityRepository userAuthorityRepository, DeliveryExecutiveStatusRepository deliveryExecutiveStatusRepository){
        this.userRepository=userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.deliveryExecutiveStatusRepository = deliveryExecutiveStatusRepository;
    }

    private UserEntity userToEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setAddress(user.getAddress());
        userEntity.setEmail(user.getEmail());
        userEntity.setCreatedAt(user.getCreatedAt());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setUpdatedAt(user.getUpdatedAt());
        return userEntity;
    }

    BiFunction<UserAuthorityEntity,Long, UserAuthority> entityToUserAuthority = (userAuthorityEntity, userId) -> {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setTypeId(userAuthorityEntity.getTypeId());
        userAuthority.setUserId(userId);
        userAuthority.setUserType(userAuthorityEntity.getUserType());
        return userAuthority;

    };


    BiFunction<UserAuthority,Long,UserAuthorityEntity> userAuthorityToEntity = (userAuthority,userId) -> {
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();
        userAuthorityEntity.setTypeId(userAuthority.getTypeId());
        userAuthorityEntity.setUserId(userId);
        userAuthorityEntity.setUserType(userAuthority.getUserType());
        return userAuthorityEntity;
    };


    private User entityToUser(UserEntity userEntity){
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setAddress(userEntity.getAddress());
        user.setCreatedAt(userEntity.getCreatedAt());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setUpdatedAt(userEntity.getUpdatedAt());
        user.setUserType(userAuthorityRepository
                .findAllByUserId(user.getUserId()).stream()
                .map(userAuthorityEntity -> entityToUserAuthority
                        .apply(userAuthorityEntity,user.getUserId()))
                .collect(Collectors.toSet()));
        return user;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        log.debug("Initiated processing et user by Id processing with userId"+userId);
        return userRepository.findById(userId).map(this::entityToUser);
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("Initiated processing to get all Users data");
        return userRepository
                .findAll().stream()
                .map(this::entityToUser)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<User> saveUser(User user) {
        log.debug("Initiated processing to save new user");
        try{
            User persistedUser = entityToUser(userRepository.save(userToEntity(user)));
            Set<UserAuthority> persistedUserAuthority = user.getUserType().stream()
                    .map(userAuthority->userAuthorityToEntity.apply(userAuthority,persistedUser.getUserId()))
                    .map(userAuthorityRepository::save)
                    .map(userAuthorityEntity -> entityToUserAuthority
                            .apply(userAuthorityEntity,persistedUser.getUserId()))
                    .collect(Collectors.toSet());
            persistedUser.setUserType(persistedUserAuthority);
            return Optional.of(persistedUser);

        }catch (Exception e){
            log.error("Exception occurred while saving user detail ",e);
            return Optional.empty();
        }

    }

    @Override
    @Transactional
    public Optional<User> updateUser(User user) {
        log.debug("Initiated processing to save new user");
        try{
            Optional<UserEntity> userEntityById = userRepository.findById(user.getUserId());
            Set<UserAuthority> userAuthorities = userAuthorityRepository.findAllByUserId(user.getUserId()).stream().map(userAuthorityEntity->entityToUserAuthority.apply(userAuthorityEntity,user.getUserId())).collect(Collectors.toSet());
            user.getUserType().removeAll(userAuthorities);
            if(userEntityById.isPresent()){
                User persistedUser = entityToUser(userRepository.save(userToEntity(user)));
                Set<UserAuthority> persistedUserAuthority = user.getUserType().stream()
                        .map(userAuthority->userAuthorityToEntity.apply(userAuthority,persistedUser.getUserId()))
                        .map(userAuthorityRepository::save)
                        .map(userAuthorityEntity -> entityToUserAuthority
                                .apply(userAuthorityEntity,persistedUser.getUserId()))
                        .collect(Collectors.toSet());
                persistedUser.setUserType(persistedUserAuthority);
                return Optional.of(persistedUser);
            }else{
                log.error("No user exists with userId:"+user.getUserId());
                return Optional.empty();
            }

        }catch (Exception e){
            log.error("Exception occurred while saving user detail ",e);
            return Optional.empty();
        }

    }

    Function<DeliveryExecutiveStatusEntity, DeliveryExecutiveStatus> entityToDeliveryExecutiveStatus = deliveryExecutiveStatusEntity -> {
        DeliveryExecutiveStatus persistedDeliveryExecutiveStatus = new DeliveryExecutiveStatus();
        persistedDeliveryExecutiveStatus.setStatusId(deliveryExecutiveStatusEntity.getStatusId());
        persistedDeliveryExecutiveStatus.setDeliveryExecutiveState(deliveryExecutiveStatusEntity.getDeliveryExecutiveState());
        persistedDeliveryExecutiveStatus.setUserId(deliveryExecutiveStatusEntity.getUserId());
        persistedDeliveryExecutiveStatus.setUpdatedLocation(deliveryExecutiveStatusEntity.getUpdatedLocation());
        return persistedDeliveryExecutiveStatus;
    };
    Function<DeliveryExecutiveStatus, DeliveryExecutiveStatusEntity> deliveryExecutiveStatusToEntity = deliveryExecutiveStatus -> {
        DeliveryExecutiveStatusEntity deliveryExecutiveStatusEntity = new DeliveryExecutiveStatusEntity();
        deliveryExecutiveStatusEntity.setStatusId(deliveryExecutiveStatus.getStatusId());
        deliveryExecutiveStatusEntity.setUserId(deliveryExecutiveStatus.getUserId());
        deliveryExecutiveStatusEntity.setUpdatedLocation(deliveryExecutiveStatus.getUpdatedLocation());
        deliveryExecutiveStatusEntity.setDeliveryExecutiveState(deliveryExecutiveStatus.getDeliveryExecutiveState());
        return deliveryExecutiveStatusEntity;
    };

    @Override
    public Optional<DeliveryExecutiveStatus> saveDeliverySExecutiveStatus(DeliveryExecutiveStatus deliveryExecutiveStatus) {
        return Optional.ofNullable(entityToDeliveryExecutiveStatus.apply(deliveryExecutiveStatusRepository
                .save(deliveryExecutiveStatusToEntity
                        .apply(deliveryExecutiveStatus))));
    }

    public Optional<DeliveryExecutiveStatus> getDeliverExecutiveState(Long userId){
        return deliveryExecutiveStatusRepository.findByUserId(userId).map(entityToDeliveryExecutiveStatus);
    }
}
