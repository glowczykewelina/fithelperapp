package com.engineer.proposition;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PropositionRepository extends JpaRepository<Proposition,Long>{

    List<Proposition> findAllByTypeMeal(String typeMeal);
    Proposition findOneByNameMealAndTypeMeal(String nameMeal, String typeMeal);

}
