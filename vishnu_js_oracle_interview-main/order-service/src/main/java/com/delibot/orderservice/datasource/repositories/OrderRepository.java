package com.delibot.orderservice.datasource.repositories;

import com.delibot.orderservice.datasource.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findByUserId(Long userId);

    List<OrderEntity> findByDelivererID(Long delivererID);


}
