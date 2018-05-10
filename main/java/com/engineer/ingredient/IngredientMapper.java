package com.engineer.ingredient;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    @Mappings({
            @Mapping(source = "proposition.nameMeal",target = "propositionName"),
            @Mapping(source = "proposition.typeMeal",target = "propositionType")
    })

    IngredientDTO toIngredientDTO (Ingredient ingredient);

    List<IngredientDTO> toIngredientDTO(Collection<Ingredient> ingredients);

}
