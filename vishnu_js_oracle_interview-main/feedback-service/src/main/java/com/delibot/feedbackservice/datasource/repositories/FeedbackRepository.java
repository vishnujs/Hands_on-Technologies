package com.delibot.feedbackservice.datasource.repositories;

import com.delibot.feedbackservice.datasource.entities.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedBackEntity,Long> {
}
