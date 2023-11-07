package com.delibot.feedbackservice.datasource.repositories;

import com.delibot.feedbackservice.datasource.entities.FeedBackBasedRankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedBackBasedRankingRepository extends JpaRepository<FeedBackBasedRankingEntity,Long> {

    Optional<FeedBackBasedRankingEntity> findByUserId(Long userId);
}
