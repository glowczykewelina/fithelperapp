package com.engineer.recommendation;

import java.util.List;

public interface RecommendationService {

    List<Recommendation> recommendMeal(String name,String ingred1, String ingret2);

}
