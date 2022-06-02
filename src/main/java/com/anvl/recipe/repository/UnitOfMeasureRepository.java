package com.anvl.recipe.repository;

import com.anvl.recipe.model.Category;
import com.anvl.recipe.model.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure,Long> {


    Optional<UnitOfMeasure> findByUom(String uom);

}
