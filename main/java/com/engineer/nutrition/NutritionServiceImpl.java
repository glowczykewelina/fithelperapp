package com.engineer.nutrition;

import com.engineer.data.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutritionServiceImpl implements NutritionService {

    @Override
    public List<Nutrition> proposeNutrition(Data data) {

        List <Nutrition> nutri = new ArrayList<Nutrition>();
        Nutrition nutrition = new Nutrition();

        //get in-data from user
        String gender = data.getGender();
        Short age = data.getAge();
        Short weight = data.getWeight();
        String activity = data.getActivity();

        // change activity from String to Integer
        double newActivity =0;
        if (activity.equals("znikoma")) {
            newActivity =1.2;
        }
        if (activity.equals("niska")) {
            newActivity =1.4;
        }
        if (activity.equals("srednia")) {
            newActivity =1.6;
        }
        if (activity.equals("wysoka")) {
            newActivity =1.8;
        }
        if (activity.equals("bardzo wysoka")) {
            newActivity = 2;
        }

        int calory = 0;
        int carbohydrate = 0;
        int protein = 0;
        int fat =0;

        if (gender.equals("Kobieta")) {
            if (age >10 & age<=17) {
                calory = (int) (((weight*0.056)+2.898)*238.846*newActivity);
                nutrition.setCalory(calory);
            } else if (age >17 & age<=29) {
                calory = (int) (((weight*0.062)+2.036)*238.846*newActivity);
                nutrition.setCalory(calory);
            } else if (age >29 & age<=59) {
                calory = (int) (((weight*0.034)+3.538)*238.846*newActivity);
                nutrition.setCalory(calory);
            } else if (age >59 & age<=74) {
                calory = (int) (((weight*0.0386)+2.875)*238.846*newActivity);
                nutrition.setCalory(calory);
            } else {
                calory = (int) (((weight*0.041)+2.61)*238.846*newActivity);
                nutrition.setCalory(calory);
            }

        } else if (gender.equals("Mezczyzna")) {
            if (age >10 & age<=17) {
                calory = (int) (((weight * 0.074) + 2.754) * 238.846 * newActivity);
                nutrition.setCalory(calory);
            } else if (age >17 & age<=29) {
                calory = (int) (((weight * 0.063) + 2.896) * 238.846 * newActivity);
                nutrition.setCalory(calory);
            } else if (age >29 & age<=59) {
                calory = (int) (((weight * 0.048) + 3.653) * 238.846 * newActivity);
                nutrition.setCalory(calory);
            }  else if (age >59 & age<=74) {
                calory = (int) (((weight*0.0499)+2.93)*238.846*newActivity);
                nutrition.setCalory(calory);
            } else {
                calory = (int) (((weight*0.035)+3.434)*238.846*newActivity);
                nutrition.setCalory(calory);
            }
        }

        carbohydrate = (int) ((calory*0.6)/4);
        nutrition.setCarbohydrote(carbohydrate);

        fat = (int) ((calory*0.28)/9);
        nutrition.setFat(fat);

        protein = (int) ((calory*0.12)/4);
        nutrition.setProtein(protein);

        nutri.add(nutrition);
        return nutri;


    }

}
