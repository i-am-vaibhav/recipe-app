package com.anvl.recipe.repository;

import com.anvl.recipe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByDescription(String category);

}
