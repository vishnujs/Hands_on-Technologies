package com.delibot.userservice.datasource.repositories;

import com.delibot.userservice.datasource.entities.DeliveryExecutiveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryExecutiveStatusRepository extends JpaRepository<DeliveryExecutiveStatusEntity,Long> {

    Optional<DeliveryExecutiveStatusEntity> findByUserId(Long userId);
}
