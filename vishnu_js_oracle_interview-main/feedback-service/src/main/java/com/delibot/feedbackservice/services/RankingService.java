package com.delibot.feedbackservice.services;

import com.delibot.feedbackservice.datasource.entities.FeedBackBasedRankingEntity;
import com.delibot.feedbackservice.web.models.FeedbackBasedRanking;
import com.delibot.feedbackservice.web.models.FeedbackKafka;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {

    List<FeedbackBasedRanking> findAllRanks();
}
