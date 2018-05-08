package com.engineer.recommendation;

import com.engineer.meal.Meal;
import com.engineer.meal.MealRepository;
import com.engineer.proposition.Proposition;
import com.engineer.proposition.PropositionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private RecommendationRepository recommendationRepository;
    private PropositionRepository propositionRepository;
    private MealRepository mealRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, PropositionRepository propositionRepository, MealRepository mealRepository) {
        this.recommendationRepository = recommendationRepository;
        this.propositionRepository = propositionRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public List<Recommendation> recommendMeal(String name,String ingred1, String ingred2) {

        Recommendation recommendations = new Recommendation();
        List<Recommendation> recommendationList = new ArrayList<>();
        String userName = name;

        Meal meal = mealRepository.findOneByName(userName);
        int userKcal = meal.getMeal_cal();
        int userProtein = meal.getMeal_protein();
        int userFat = meal.getMeal_fat();
        int userCarbo = meal.getMeal_carbo();
        String userType = meal.getTypeMeal();

        //System.out.println(userKcal);

        List<Proposition> propositions = propositionRepository.findAllByTypeMeal(userType);
        int[] remainderKcal = new int[propositions.size()];
        int[] remainderProtein = new int[propositions.size()];
        int[] remainderFat = new int[propositions.size()];
        int[] remainderCarbo = new int[propositions.size()];
        int[] remainderNutri = new int[propositions.size()];

        //System.out.println(propositions.size());

        for (int i =0; i < propositions.size();i++) {
            Proposition proposition = propositions.get(i);
            int propoKcal = proposition.getKcal();
            int propoProtein = proposition.getProtein();
            int propoFat =proposition.getFat();
            int propoCarbo = proposition.getCarbohydrote();

           // System.out.println(propoKcal);

            remainderKcal[i] = abs(userKcal-propoKcal);
            //if (remainderKcal[i]<0){
            //    remainderKcal[i]=remainderKcal[i]*(-1);
            //}else {
            //    remainderKcal[i]=remainderKcal[i];
            //}
            remainderProtein[i] = abs(userProtein-propoProtein);
            remainderFat[i] = abs(userFat-propoFat);
            remainderCarbo[i] = abs(userCarbo-propoCarbo);
            remainderNutri[i] = remainderKcal[i] + remainderCarbo[i] + remainderFat[i]+remainderProtein[i];

            //System.out.println(remainderKcal[i]);
            System.out.println(remainderNutri[i]);

         //   int score = remainderNutri[0];
         //   for (int j=1; j<propositions.size();j++) {
         //       if (score > remainderNutri[j]) {
         //           score = remainderNutri[j];
         //       }
         //   }

            int condition = 100;
            int firstOptionNutri =0;
            if (remainderNutri[i]<condition){
                firstOptionNutri = remainderNutri[i];
            } else {
                firstOptionNutri = 0;
            }

            //System.out.println(firstOptionNutri);

            if (firstOptionNutri==(remainderNutri[i])) {
                int indexMeal = i;
                Proposition propositionRecommended = propositions.get(indexMeal);
                recommendations.setNameMeal(propositionRecommended.getNameMeal());
                recommendations.setWeight(propositionRecommended.getWeight());
                recommendations.setKcal(propositionRecommended.getKcal());
                recommendations.setProtein(propositionRecommended.getProtein());
                recommendations.setFat(propositionRecommended.getFat());
                recommendations.setCarbohydrote(propositionRecommended.getCarbohydrote());
                recommendations.setTypeMeal(propositionRecommended.getTypeMeal());
                recommendationList.add(recommendations);
            }

        }

        return recommendationRepository.save(recommendationList);
    }
}
