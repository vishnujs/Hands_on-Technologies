package com.delibot.orderservice.datasource.repositories;

import com.delibot.orderservice.datasource.entities.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBackEntity,Long> {
}
