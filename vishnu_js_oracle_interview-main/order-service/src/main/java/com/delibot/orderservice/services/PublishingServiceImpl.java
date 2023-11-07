package com.delibot.orderservice.services;

import com.delibot.orderservice.web.models.FeedbackKafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class PublishingServiceImpl implements PublishingService {

    private static final String TOPIC = "testTopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publish(FeedbackKafka feedbackKafka) {
        log.info("publishing payload :{}", feedbackKafka);
        try {

            ListenableFuture<SendResult<String, String>> future
                    = kafkaTemplate.send(MessageBuilder.withPayload(feedbackKafka.toString()).setHeader(KafkaHeaders.TOPIC, TOPIC).build());

            future.addCallback(new ListenableFutureCallback() {
                @Override
                public void onSuccess(Object o) {
                    log.info("payload published to kafka");
                }

                @Override
                public void onFailure(final Throwable throwable) {
                    // left empty intentionally
                    log.error("error in publishing payload to kafka; caused by : ", throwable);
                }
            });

        } catch (Exception exc) {
            log.error("error in publishing interview schedule with payload :{} caused by {}"
                    , feedbackKafka, exc);
        }

    }
}
