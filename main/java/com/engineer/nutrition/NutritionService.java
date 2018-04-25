package com.engineer.nutrition;

import com.engineer.data.Data;

import java.util.List;

public interface NutritionService {

    List<NutritionDTO> proposeNutrition (Data data);

    NutritionDTO findOneNutrition(String name);
}
