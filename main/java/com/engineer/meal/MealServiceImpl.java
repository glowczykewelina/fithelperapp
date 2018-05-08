package com.engineer.meal;

import com.engineer.data.DataMeal;
import com.engineer.nutrition.Nutrition;
import com.engineer.nutrition.NutritionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealMapper mealMapper;
    private MealRepository mealRepository;
    private NutritionRepository nutritionRepository;

    public MealServiceImpl(MealMapper mealMapper, MealRepository mealRepository, NutritionRepository nutritionRepository) {
        this.mealMapper = mealMapper;
        this.mealRepository = mealRepository;
        this.nutritionRepository = nutritionRepository;
    }

    @Override
    public List<MealDTO> calculateMeal(DataMeal dataMeal, String name) {

        List<Meal> meals = new ArrayList<>();
        Meal meal = new Meal();
        String typeMeal = dataMeal.getType();
        int mealNumber = dataMeal.getNumber_of_meals();
        meal.setTypeMeal(typeMeal);
        meal.setNumberMeal(mealNumber);
        meal.setName(name);

        Nutrition nutrition = nutritionRepository.findOneByName(name);
        int userCal = nutrition.getCalory();
        int userProtein = nutrition.getProtein();
        int userFat = nutrition.getFat();
        int userCarbo = nutrition.getCarbohydrote();

        if (mealNumber==3) {
            if (typeMeal.equals("sniadanie")){
                meal.setMeal_cal((int) (userCal*0.35));
                meal.setMeal_protein((int) (userProtein*0.35));
                meal.setMeal_carbo((int) (userCarbo*0.35));
                meal.setMeal_fat((int) (userFat*0.35));
            }else if (typeMeal.equals("obiad")){
                meal.setMeal_cal((int) (userCal*0.4));
                meal.setMeal_protein((int) (userProtein*0.4));
                meal.setMeal_carbo((int) (userCarbo*0.4));
                meal.setMeal_fat((int) (userFat*0.4));
            }else if (typeMeal.equals("kolacja")){
                meal.setMeal_cal((int) (userCal*0.25));
                meal.setMeal_protein((int) (userProtein*0.25));
                meal.setMeal_carbo((int) (userCarbo*0.25));
                meal.setMeal_fat((int) (userFat*0.25));
            }
        }else if (mealNumber==4) {
            if (typeMeal.equals("sniadanie")){
                meal.setMeal_cal((int) (userCal*0.30));
                meal.setMeal_protein((int) (userProtein*0.30));
                meal.setMeal_carbo((int) (userCarbo*0.30));
                meal.setMeal_fat((int) (userFat*0.30));
            }else if (typeMeal.equals("drugie sniadanie")){
                meal.setMeal_cal((int) (userCal*0.05));
                meal.setMeal_protein((int) (userProtein*0.05));
                meal.setMeal_carbo((int) (userCarbo*0.05));
                meal.setMeal_fat((int) (userFat*0.05));
            }else if (typeMeal.equals("obiad")){
                meal.setMeal_cal((int) (userCal*0.4));
                meal.setMeal_protein((int) (userProtein*0.4));
                meal.setMeal_carbo((int) (userCarbo*0.4));
                meal.setMeal_fat((int) (userFat*0.4));
            }else if (typeMeal.equals("kolacja")){
                meal.setMeal_cal((int) (userCal*0.25));
                meal.setMeal_protein((int) (userProtein*0.25));
                meal.setMeal_carbo((int) (userCarbo*0.25));
                meal.setMeal_fat((int) (userFat*0.25));
            }
        }else if (mealNumber==5) {
            if (typeMeal.equals("sniadanie")){
                meal.setMeal_cal((int) (userCal*0.30));
                meal.setMeal_protein((int) (userProtein*0.30));
                meal.setMeal_carbo((int) (userCarbo*0.30));
                meal.setMeal_fat((int) (userFat*0.30));
            }else if (typeMeal.equals("drugie sniadanie")){
                meal.setMeal_cal((int) (userCal*0.1));
                meal.setMeal_protein((int) (userProtein*0.1));
                meal.setMeal_carbo((int) (userCarbo*0.1));
                meal.setMeal_fat((int) (userFat*0.1));
            }else if (typeMeal.equals("obiad")){
                meal.setMeal_cal((int) (userCal*0.4));
                meal.setMeal_protein((int) (userProtein*0.4));
                meal.setMeal_carbo((int) (userCarbo*0.4));
                meal.setMeal_fat((int) (userFat*0.4));
            }else if (typeMeal.equals("podwieczorek")){
                meal.setMeal_cal((int) (userCal*0.05));
                meal.setMeal_protein((int) (userProtein*0.05));
                meal.setMeal_carbo((int) (userCarbo*0.05));
                meal.setMeal_fat((int) (userFat*0.05));
            } else if (typeMeal.equals("kolacja")){
                meal.setMeal_cal((int) (userCal*0.15));
                meal.setMeal_protein((int) (userProtein*0.15));
                meal.setMeal_carbo((int) (userCarbo*0.15));
                meal.setMeal_fat((int) (userFat*0.15));
            }
        }
        meals.add(meal);
        return mealMapper.toMealDTO(mealRepository.save(meals));

    }

    @Override
    public MealDTO findOneMealByName (String name) {

        return mealMapper.toMealDTO(mealRepository.findOneByName(name));


    }

}
