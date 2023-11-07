package com.delibot.orderservice.datasource.repositories;

import com.delibot.orderservice.datasource.entities.DeliveryExecutiveState;
import com.delibot.orderservice.datasource.entities.DeliveryExecutiveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryExecutiveStatusRepository extends JpaRepository<DeliveryExecutiveStatusEntity,Long> {

    List<DeliveryExecutiveStatusEntity> findByUpdatedLocationAndDeliveryExecutiveState(String location, DeliveryExecutiveState deliveryExecutiveState);
}
