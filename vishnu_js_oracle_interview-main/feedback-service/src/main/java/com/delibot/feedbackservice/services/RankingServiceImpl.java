package com.delibot.feedbackservice.services;

import com.delibot.feedbackservice.datasource.entities.FeedBackBasedRankingEntity;
import com.delibot.feedbackservice.datasource.repositories.FeedBackBasedRankingRepository;
import com.delibot.feedbackservice.web.models.FeedbackBasedRanking;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RankingServiceImpl implements RankingService{

    private final FeedBackBasedRankingRepository feedBackBasedRankingRepository;

    public RankingServiceImpl(FeedBackBasedRankingRepository feedBackBasedRankingRepository) {
        this.feedBackBasedRankingRepository = feedBackBasedRankingRepository;
    }

    Function<FeedBackBasedRankingEntity,FeedbackBasedRanking> entityToFeedbackBasedRanking = feedBackBasedRankingEntity -> {
        FeedbackBasedRanking feedbackBasedRanking = new FeedbackBasedRanking();
        feedbackBasedRanking.setUserId(feedBackBasedRankingEntity.getUserId());
        feedbackBasedRanking.setTotalDeliveryCount(feedBackBasedRankingEntity.getTotalDeliveryCount());
        feedbackBasedRanking.setAverageRating(feedBackBasedRankingEntity.getAverageRating());
        feedbackBasedRanking.setRankId(feedBackBasedRankingEntity.getRankId());
        return feedbackBasedRanking;
    };

    @Override
    public List<FeedbackBasedRanking> findAllRanks() {
        return feedBackBasedRankingRepository.findAll().stream().map(entityToFeedbackBasedRanking).collect(Collectors.toList());

    }
}
