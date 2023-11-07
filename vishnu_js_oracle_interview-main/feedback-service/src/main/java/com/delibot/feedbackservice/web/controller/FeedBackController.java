package com.delibot.feedbackservice.web.controller;

import com.delibot.feedbackservice.services.RankingService;
import com.delibot.feedbackservice.web.models.FeedbackBasedRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedBackController {

    private final RankingService rankingService;

    @Autowired
    public FeedBackController(RankingService rankingService) {
        this.rankingService = rankingService;
    }


    @GetMapping("/allRanks")
    public List<FeedbackBasedRanking> feedbackBasedRankings(){
        return rankingService.findAllRanks();
    }
}
