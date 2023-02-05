package com.anvl.recipe.service;

import com.anvl.recipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findIngredient(Long recipeId, Long ingredientId);

    void save(IngredientCommand ingredient) throws Exception;

    void deleteById(Long ingredientId);
}
