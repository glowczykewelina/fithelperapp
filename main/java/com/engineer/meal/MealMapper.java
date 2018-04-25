package com.engineer.meal;

import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MealMapper {

    MealDTO toMealDTO (Meal meal);

    List<MealDTO> toMealDTO(Collection<Meal> meals);

}
