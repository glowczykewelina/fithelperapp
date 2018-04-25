package com.engineer.meal;

import com.engineer.data.DataMeal;

import java.util.List;

public interface MealService {

    List<MealDTO> calculateMeal (DataMeal dataMeal, String name);
}
