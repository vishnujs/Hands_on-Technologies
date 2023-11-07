package com.delibot.feedbackservice.web.models;



public class Feedback {

    private Long feedBackId;

    private Long orderId;

    private Integer feedbackPointsStore;

    private Integer feedbackPointsDelivery;


    public Long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

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
}
