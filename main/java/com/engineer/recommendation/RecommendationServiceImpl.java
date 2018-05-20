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

            int condition = 100;
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


        //int sizeNutri = recomendationWithNutri.size();
        //int sizeIngred1 = recommendedWithIngredient1.size();
        //System.out.println(sizeIngred1);
        //int sizeIngred2 = recommendedWithIngredient2.size();
        //System.out.println(sizeIngred2);
        //for (int i =0; i< sizeNutri;i++) {
        //    for (int j=0; j<sizeIngred1;j++) {
        //        if (recomendationWithNutri.get(i).equals(recommendedWithIngredient1.get(j))) {
        //            recommendedWithIngredient1.remove(j);
        //            sizeIngred1=sizeIngred1-1;
        //        }
        //    }
        //}

        //for (int i =0; i< sizeNutri;i++) {
        //    for (int j=0; j<sizeIngred2;j++) {
        //        if (recomendationWithNutri.get(i).equals(recommendedWithIngredient2.get(j))) {
        //            recommendedWithIngredient2.remove(j);
        //            sizeIngred2=sizeIngred2-1;
        //        }
        //    }
        //}

        //recommendationList.addAll(recomendationWithNutri);
        //recommendationList.addAll(recommendedWithIngredient1);
        //recommendationList.addAll(recommendedWithIngredient2);

       // for (int i=0; i<recommendationList.size(); i++) {
        //    Recommendation recommendation = recommendationList.get(i);
        //    if
       // }


       // List<Recommendation> theBestMeal = new ArrayList<>();
        for (int i=0; i<recommendationList.size();i++) {
            for (int j=i+1; j <recommendationList.size();j++) {

                if (recommendationList.get(i).equals(recommendationList.get(j))) {
                    recommendationList.remove(j);
                    j--;
                } else if (!recommendationList.get(i).equals(recommendationList.get(j))) {
                    Recommendation recommendation1 = recommendationList.get(i);
                    Recommendation recommendation2 = recommendationList.get(j);
                    if (recommendation1.getNameMeal()==recommendation2.getNameMeal()) {
                        if (recommendation1.getMatchPercentage()>recommendation2.getMatchPercentage()) {
                            recommendationList.remove(j) ;
                            j--;
                        } else if (recommendation1.getMatchPercentage()<recommendation2.getMatchPercentage()) {
                            recommendationList.remove(i);
                            i--;
                        }
                    }
                }

            }
        }


        return recommendationRepository.save(recommendationList);

    }
}
