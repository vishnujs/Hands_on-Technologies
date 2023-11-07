package com.delibot.feedbackservice.datasource.entities;

import javax.persistence.*;

@Entity
@Table(name = "FEEDBACK")
public class FeedBackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FEEDBACK_ID")
    private Long feedBackId;

    @Column(name="ORDER_ID")
    private Long orderId;

    @Column(name="FEEDBACK_POINTS_STORE")
    private Integer feedbackPointsStore;


    @Column(name="FEEDBACK_POINTS_DELIVERY")
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
