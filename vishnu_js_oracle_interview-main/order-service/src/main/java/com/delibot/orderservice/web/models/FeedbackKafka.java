package com.delibot.orderservice.web.models;

import java.io.Serializable;

public class FeedbackKafka implements Serializable {


    private Long orderId;

    private Integer feedbackPointsStore;

    private Integer feedbackPointsDelivery;

    private Long deliveryExecutiveId;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getFeedbackPointsStore() {
        return feedbackPointsStore;
    }

    public void setFeedbackPointsStore(Integer feedbackPointsStore) {
        this.feedbackPointsStore = feedbackPointsStore;
    }

    public Integer getFeedbackPointsDelivery() {
        return feedbackPointsDelivery;
    }

    public void setFeedbackPointsDelivery(Integer feedbackPointsDelivery) {
        this.feedbackPointsDelivery = feedbackPointsDelivery;
    }

    public Long getDeliveryExecutiveId() {
        return deliveryExecutiveId;
    }

    public void setDeliveryExecutiveId(Long deliveryExecutiveId) {
        this.deliveryExecutiveId = deliveryExecutiveId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"orderId\":" + orderId +
                ", \"deliveryExecutiveId\":" + deliveryExecutiveId +
                ", \"feedbackPointsStore\":" + feedbackPointsStore +
                ", \"feedbackPointsDelivery\":" + feedbackPointsDelivery +
                '}';
    }
}
