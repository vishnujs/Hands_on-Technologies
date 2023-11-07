package com.delibot.feedbackservice.services;

import com.delibot.feedbackservice.datasource.entities.FeedBackBasedRankingEntity;
import com.delibot.feedbackservice.datasource.entities.FeedBackEntity;
import com.delibot.feedbackservice.datasource.repositories.FeedBackBasedRankingRepository;
import com.delibot.feedbackservice.datasource.repositories.FeedbackRepository;
import com.delibot.feedbackservice.web.models.FeedbackKafka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private final FeedBackBasedRankingRepository feedBackBasedRankingRepository;

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public Receiver(FeedBackBasedRankingRepository feedBackBasedRankingRepository, FeedbackRepository feedbackRepository) {
        this.feedBackBasedRankingRepository = feedBackBasedRankingRepository;
        this.feedbackRepository = feedbackRepository;
    }

    private static final String TOPIC = "testTopic";


    @KafkaListener(topics = TOPIC)
    public void receiveSchedule(String feedbackKafkaString) {

        ObjectMapper objectMapper = new ObjectMapper();
        FeedbackKafka feedbackKafka=null;
        try {
            feedbackKafka = objectMapper.readValue(feedbackKafkaString, FeedbackKafka.class);
            processFeedBack(feedbackKafka);

        } catch (JsonProcessingException e) {
            logger.error("Unable to parse the json",e);
        }
        logger.info(" received feedback = [{}]",feedbackKafka!=null?feedbackKafka.toString() :"");
    }

    @Transactional
    private void processFeedBack(FeedbackKafka feedbackKafka) {
        //get feedback from kafka, get delivery_executive rank if present computer average else create new entry
        feedBackBasedRankingRepository.findByUserId(feedbackKafka.getDeliveryExecutiveId()).map(feedBackBasedRankingEntity -> {
            int computedAverageRating = ((feedBackBasedRankingEntity.getTotalDeliveryCount() * feedBackBasedRankingEntity.getAverageRating())
                    + feedbackKafka.getFeedbackPointsDelivery()) / (feedBackBasedRankingEntity.getTotalDeliveryCount() + 1);
            feedBackBasedRankingEntity.setAverageRating(computedAverageRating);
            feedBackBasedRankingEntity.setTotalDeliveryCount(feedBackBasedRankingEntity.getTotalDeliveryCount() + 1);
            return feedBackBasedRankingRepository.save(feedBackBasedRankingEntity);
        }).orElseGet(() -> {
            FeedBackBasedRankingEntity feedBackBasedRankingEntity = new FeedBackBasedRankingEntity();
            feedBackBasedRankingEntity.setTotalDeliveryCount(1);
            feedBackBasedRankingEntity.setAverageRating(feedbackKafka.getFeedbackPointsDelivery());
            feedBackBasedRankingEntity.setUserId(feedbackKafka.getDeliveryExecutiveId());
            return feedBackBasedRankingRepository.save(feedBackBasedRankingEntity);
        });

        //persist the feedback from user
        FeedBackEntity feedBackEntity = new FeedBackEntity();
        feedBackEntity.setFeedbackPointsDelivery(feedbackKafka.getFeedbackPointsDelivery());
        feedBackEntity.setFeedbackPointsStore(feedbackKafka.getFeedbackPointsStore());
        feedBackEntity.setOrderId(feedbackKafka.getOrderId());
        feedbackRepository.save(feedBackEntity);
    }
}
