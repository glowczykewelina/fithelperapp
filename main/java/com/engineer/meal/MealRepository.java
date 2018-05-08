package com.engineer.meal;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface MealRepository extends JpaRepository<Meal,Long> {

    Meal findOneByName (String name);



}
