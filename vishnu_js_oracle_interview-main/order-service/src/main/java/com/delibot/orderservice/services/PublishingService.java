package com.delibot.orderservice.services;

import com.delibot.orderservice.web.models.FeedbackKafka;

public interface PublishingService {
    void publish(FeedbackKafka feedbackKafka);
}
