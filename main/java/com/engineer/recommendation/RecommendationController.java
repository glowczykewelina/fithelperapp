package com.engineer.recommendation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    List<Recommendation> recommendMeal(@RequestParam String name, @RequestParam String inged1, @RequestParam String ingred2) {
        return recommendationService.recommendMeal(name,inged1,ingred2);
    }
}
