package com.delibot.feedbackservice.datasource.entities;

import javax.persistence.*;

@Entity
@Table(name = "FEEDBACK_BASED_RANKING")
public class FeedBackBasedRankingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RANK_ID")
    private Long rankId;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="AVERAGE_RATING")
    private Integer averageRating;


    @Column(name="TOTAL_DELIVERY_COUNT")
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
