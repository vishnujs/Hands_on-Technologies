package com.delibot.feedbackservice.web.models;


public class FeedbackBasedRanking {

    private Long rankId;

    private Long userId;

    private Integer averageRating;

    private Integer totalDeliveryCount;

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getTotalDeliveryCount() {
        return totalDeliveryCount;
    }

    public void setTotalDeliveryCount(Integer totalDeliveryCount) {
        this.totalDeliveryCount = totalDeliveryCount;
    }
}
