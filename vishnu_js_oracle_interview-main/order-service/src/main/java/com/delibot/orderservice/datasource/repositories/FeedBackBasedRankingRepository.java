package com.delibot.orderservice.datasource.repositories;

import com.delibot.orderservice.datasource.entities.FeedBackBasedRankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedBackBasedRankingRepository extends JpaRepository<FeedBackBasedRankingEntity,Long> {

    Optional<FeedBackBasedRankingEntity> findByUserId(Long userId);

    List<FeedBackBasedRankingEntity> findByUserIdIn(List<Long> userIds);

    Optional<FeedBackBasedRankingEntity> findTop1ByUserIdInOrderByAverageRatingDesc(List<Long> userIds);

}
