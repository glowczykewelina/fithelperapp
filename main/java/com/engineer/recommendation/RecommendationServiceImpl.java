package com.engineer.recommendation;

import com.engineer.ingredient.Ingredient;
import com.engineer.ingredient.IngredientRepository;
import com.engineer.meal.Meal;
import com.engineer.meal.MealRepository;
import com.engineer.proposition.Proposition;
import com.engineer.proposition.PropositionDTO;
import com.engineer.proposition.PropositionRepository;
import com.engineer.proposition.PropositionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private RecommendationRepository recommendationRepository;
    private PropositionRepository propositionRepository;
    private MealRepository mealRepository;
    private PropositionService propositionService;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, PropositionRepository propositionRepository, MealRepository mealRepository, PropositionService propositionService) {
        this.recommendationRepository = recommendationRepository;
        this.propositionRepository = propositionRepository;
        this.mealRepository = mealRepository;
        this.propositionService = propositionService;
    }

    @Override
    public List<Recommendation> recommendMeal(String name,String ingred1, String ingred2) {


        List<Recommendation> recommendationList = new ArrayList<>();
        String userName = name;


        Meal meal = mealRepository.findOneByName(userName);
        int userKcal = meal.getMeal_cal();
        int userProtein = meal.getMeal_protein();
        int userFat = meal.getMeal_fat();
        int userCarbo = meal.getMeal_carbo();
        String userType = meal.getTypeMeal();
        System.out.println(userType);
        List<Proposition> propositions = propositionRepository.findAllByTypeMeal(userType);
        int[] remainderKcal = new int[propositions.size()];
        int[] remainderProtein = new int[propositions.size()];
        int[] remainderFat = new int[propositions.size()];
        int[] remainderCarbo = new int[propositions.size()];
        int[] remainderNutri = new int[propositions.size()];

        List<Proposition> recomendationWithNutri = new ArrayList<>();
        for (int i =0; i < propositions.size();i++) {
            Proposition proposition = propositions.get(i);
            int propoKcal = proposition.getKcal();
            int propoProtein = proposition.getProtein();
            int propoFat =proposition.getFat();
            int propoCarbo = proposition.getCarbohydrote();

            remainderKcal[i] = abs(userKcal-propoKcal);
            remainderProtein[i] = abs(userProtein-propoProtein);
            remainderFat[i] = abs(userFat-propoFat);
            remainderCarbo[i] = abs(userCarbo-propoCarbo);
            remainderNutri[i] = remainderKcal[i] + remainderCarbo[i] + remainderFat[i]+remainderProtein[i];

            int condition = 0;
            int addSum = 0;
            for (int j = 0; j < remainderNutri.length;j++){
                addSum +=remainderNutri[j];
                condition = addSum/remainderNutri.length;
            }
            System.out.println(condition);
            int firstOptionNutri =0;
            if (remainderNutri[i]<condition){
                firstOptionNutri = remainderNutri[i];
            } else {
                firstOptionNutri = 0;
            }

            if (firstOptionNutri==(remainderNutri[i])) {
                int indexMeal = i;
                Proposition propositionRecommended = propositions.get(indexMeal);
                Proposition recommendation = new Proposition();
                recommendation.setNameMeal(propositionRecommended.getNameMeal());
                recommendation.setTypeMeal(propositionRecommended.getTypeMeal());
                recommendation.setCarbohydrote(propositionRecommended.getCarbohydrote());
                recommendation.setFat(propositionRecommended.getFat());
                recommendation.setProtein(propositionRecommended.getProtein());
                recommendation.setKcal(propositionRecommended.getKcal());
                recommendation.setWeight(propositionRecommended.getWeight());
                recomendationWithNutri.add(recommendation);
            }

        }

        List<PropositionDTO> propositionWithIngred1 = propositionService.findAllPropositionByIngredient(ingred1,userType);
        List<Proposition> recommendedWithIngredient1 = new ArrayList<>();
        for (int i =0; i < propositionWithIngred1.size();i++) {
            PropositionDTO proposition = propositionWithIngred1.get(i);
            if (userType.equals(proposition.getTypeMeal())){
                Proposition recommendation = new Proposition();
                recommendation.setNameMeal(proposition.getNameMeal());
                recommendation.setTypeMeal(proposition.getTypeMeal());
                recommendation.setCarbohydrote(proposition.getCarbohydrote());
                recommendation.setFat(proposition.getFat());
                recommendation.setProtein(proposition.getProtein());
                recommendation.setKcal(proposition.getKcal());
                recommendation.setWeight(proposition.getWeight());
                recommendedWithIngredient1.add(recommendation);
                System.out.println(proposition.getNameMeal());
            }
        }

        List<PropositionDTO> propositionWithIngred2 = propositionService.findAllPropositionByIngredient(ingred2,userType);
        List<Proposition> recommendedWithIngredient2 = new ArrayList<>();

        for (int i =0; i < propositionWithIngred2.size();i++) {
            PropositionDTO proposition = propositionWithIngred2.get(i);
            if (userType.equals(proposition.getTypeMeal())) {
                Proposition recommendation = new Proposition();
                recommendation.setNameMeal(proposition.getNameMeal());
                recommendation.setTypeMeal(proposition.getTypeMeal());
                recommendation.setCarbohydrote(proposition.getCarbohydrote());
                recommendation.setFat(proposition.getFat());
                recommendation.setProtein(proposition.getProtein());
                recommendation.setKcal(proposition.getKcal());
                recommendation.setWeight(proposition.getWeight());
                recommendedWithIngredient2.add(recommendation);
            }
        }

        int sizeWithNutri = recomendationWithNutri.size();
        System.out.println(sizeWithNutri);
        int sizeWithIngred1 = recommendedWithIngredient1.size();
        System.out.println(sizeWithIngred1);
        int sizeWithIngred2 = recommendedWithIngredient2.size();
        System.out.println(sizeWithIngred2);

        if (sizeWithIngred1>0 && sizeWithIngred2>0) {


            for (int i=0; i<sizeWithIngred1;i++) {
                for (int j=0; j<sizeWithIngred2;j++) {

                    Proposition recommWithIngred1 = recommendedWithIngredient1.get(i);
                    Proposition recommWithIngred2 = recommendedWithIngredient2.get(j);

                    if (sizeWithNutri>0) {
                        for (int k = 0; k < sizeWithNutri; k++) {

                            Proposition recommWithNutri = recomendationWithNutri.get(k);

                            if (recommWithIngred2.equals(recommWithIngred1)) {
                                if (recommWithNutri.equals(recommWithIngred2)) {
                                    Recommendation recommendations1 = new Recommendation();
                                    recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                                    recommendations1.setMatchPercentage(100);
                                    recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                                    recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                                    recommendations1.setFat(recommWithNutri.getFat());
                                    recommendations1.setProtein(recommWithNutri.getProtein());
                                    recommendations1.setKcal(recommWithNutri.getKcal());
                                    recommendations1.setWeight(recommWithNutri.getWeight());
                                    recommendationList.add(recommendations1);
                                    System.out.println("a");
                                }
                            } else {
                                if (recommWithNutri.equals(recommWithIngred1)){
                                    Recommendation recommendations1 = new Recommendation();
                                    recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                                    recommendations1.setMatchPercentage(70);
                                    recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                                    recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                                    recommendations1.setFat(recommWithNutri.getFat());
                                    recommendations1.setProtein(recommWithNutri.getProtein());
                                    recommendations1.setKcal(recommWithNutri.getKcal());
                                    recommendations1.setWeight(recommWithNutri.getWeight());
                                    recommendationList.add(recommendations1);
                                    System.out.println("c");
                                } else if (recommWithNutri.equals(recommWithIngred2)) {
                                    Recommendation recommendations1 = new Recommendation();
                                    recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                                    recommendations1.setMatchPercentage(70);
                                    recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                                    recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                                    recommendations1.setFat(recommWithNutri.getFat());
                                    recommendations1.setProtein(recommWithNutri.getProtein());
                                    recommendations1.setKcal(recommWithNutri.getKcal());
                                    recommendations1.setWeight(recommWithNutri.getWeight());
                                    recommendationList.add(recommendations1);
                                    System.out.println("d");
                                } else if ((!recommWithNutri.equals(recommWithIngred2))&&(!recommWithNutri.equals(recommWithIngred1))){
                                    Recommendation recommendations1 = new Recommendation();
                                    recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                                    recommendations1.setMatchPercentage(40);
                                    recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                                    recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                                    recommendations1.setFat(recommWithNutri.getFat());
                                    recommendations1.setProtein(recommWithNutri.getProtein());
                                    recommendations1.setKcal(recommWithNutri.getKcal());
                                    recommendations1.setWeight(recommWithNutri.getWeight());
                                    recommendationList.add(recommendations1);
                                    System.out.println("c");
                                }
                            }
                        }
                    } else if (sizeWithNutri==0) {
                        if (recommWithIngred1.equals(recommWithIngred2)) {
                            Recommendation recommendations1 = new Recommendation();
                            recommendations1.setNameMeal(recommWithIngred2.getNameMeal());
                            recommendations1.setMatchPercentage(60);
                            recommendations1.setTypeMeal(recommWithIngred2.getTypeMeal());
                            recommendations1.setCarbohydrote(recommWithIngred2.getCarbohydrote());
                            recommendations1.setFat(recommWithIngred2.getFat());
                            recommendations1.setProtein(recommWithIngred2.getProtein());
                            recommendations1.setKcal(recommWithIngred2.getKcal());
                            recommendations1.setWeight(recommWithIngred2.getWeight());
                            recommendationList.add(recommendations1);
                            System.out.println("hk");
                        }
                    }
                }
            }
        } else if (sizeWithIngred1==0) {

            for (int i=0; i<sizeWithNutri;i++) {
                for (int j=0; j<sizeWithIngred2; j++) {
                    Proposition recommWithIngred2= recommendedWithIngredient2.get(j);
                    Proposition recommWithNutri = recomendationWithNutri.get(i);
                    System.out.println(recommWithNutri.getNameMeal());
                    System.out.println(recommWithIngred2.getNameMeal());

                    if (recommWithNutri.equals(recommWithIngred2)) {
                        Recommendation recommendations1 = new Recommendation();
                        recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                        recommendations1.setMatchPercentage(70);
                        recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                        recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                        recommendations1.setFat(recommWithNutri.getFat());
                        recommendations1.setProtein(recommWithNutri.getProtein());
                        recommendations1.setKcal(recommWithNutri.getKcal());
                        recommendations1.setWeight(recommWithNutri.getWeight());
                        recommendationList.add(recommendations1);
                        System.out.println("f");
                    } else {
                        Recommendation recommendations1 = new Recommendation();
                        recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                        recommendations1.setMatchPercentage(40);
                        recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                        recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                        recommendations1.setFat(recommWithNutri.getFat());
                        recommendations1.setProtein(recommWithNutri.getProtein());
                        recommendations1.setKcal(recommWithNutri.getKcal());
                        recommendations1.setWeight(recommWithNutri.getWeight());
                        recommendationList.add(recommendations1);
                        System.out.println("g");
                    }
                }
            }
        } else if (sizeWithIngred2==0) {

            for (int i=0; i<sizeWithIngred1;i++) {
                for (int j=0; j<sizeWithNutri; j++) {
                    Proposition recommWithIngred1= recommendedWithIngredient1.get(i);
                    Proposition recommWithNutri = recomendationWithNutri.get(j);

                    if (recommWithIngred1.equals(recommWithNutri)) {
                        Recommendation recommendations1 = new Recommendation();
                        recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                        recommendations1.setMatchPercentage(70);
                        recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                        recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                        recommendations1.setFat(recommWithNutri.getFat());
                        recommendations1.setProtein(recommWithNutri.getProtein());
                        recommendations1.setKcal(recommWithNutri.getKcal());
                        recommendations1.setWeight(recommWithNutri.getWeight());
                        recommendationList.add(recommendations1);
                        System.out.println("h");
                    } else {
                        Recommendation recommendations1 = new Recommendation();
                        recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                        recommendations1.setMatchPercentage(40);
                        recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                        recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                        recommendations1.setFat(recommWithNutri.getFat());
                        recommendations1.setProtein(recommWithNutri.getProtein());
                        recommendations1.setKcal(recommWithNutri.getKcal());
                        recommendations1.setWeight(recommWithNutri.getWeight());
                        recommendationList.add(recommendations1);
                        System.out.println("i");
                    }
                }
            }
        }

        if (sizeWithIngred1==0 && sizeWithIngred2==0){

            for (int i=0; i<sizeWithNutri; i++) {

                Proposition recommWithNutri = recomendationWithNutri.get(i);
                Recommendation recommendations1 = new Recommendation();
                recommendations1.setNameMeal(recommWithNutri.getNameMeal());
                recommendations1.setMatchPercentage(40);
                recommendations1.setTypeMeal(recommWithNutri.getTypeMeal());
                recommendations1.setCarbohydrote(recommWithNutri.getCarbohydrote());
                recommendations1.setFat(recommWithNutri.getFat());
                recommendations1.setProtein(recommWithNutri.getProtein());
                recommendations1.setKcal(recommWithNutri.getKcal());
                recommendations1.setWeight(recommWithNutri.getWeight());
                recommendationList.add(recommendations1);
                System.out.println("j");
            }

        }

        java.util.List<Recommendation> uniqueRecommendation = recommendationList.stream()
                .distinct()
                .collect(Collectors.toList());

        for (int i=0; i<uniqueRecommendation.size();i++) {
            for (int j=i+1; j <uniqueRecommendation.size();j++) {

                if (!uniqueRecommendation.get(i).equals(uniqueRecommendation.get(j))) {
                    Recommendation recommendation1 = uniqueRecommendation.get(i);
                    Recommendation recommendation2 = uniqueRecommendation.get(j);
                    if (recommendation1.getNameMeal()==recommendation2.getNameMeal()) {
                        if (recommendation1.getMatchPercentage()>recommendation2.getMatchPercentage()) {
                            uniqueRecommendation.remove(j) ;
                            j--;
                        } else if (recommendation1.getMatchPercentage()<recommendation2.getMatchPercentage()) {
                            uniqueRecommendation.remove(i);
                            i--;
                        }
                    }
                }
            }
        }

        Collections.sort(uniqueRecommendation, new Comparator<Recommendation>() {
            @Override
            public int compare(Recommendation o1, Recommendation o2) {
                return o2.getMatchPercentage()-o1.getMatchPercentage();
            }
        });


        return recommendationRepository.save(uniqueRecommendation);

    }
}
