package com.engineer.nutrition;

import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NutritionMapper {

    NutritionDTO toNutritionDTO(Nutrition nutrition);

    List<NutritionDTO> toNutritionDTO(Collection<Nutrition> nutritions);


}
