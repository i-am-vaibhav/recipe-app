package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Recipe findById(Long id);

    RecipeCommand save(RecipeCommand recipe);

    RecipeCommand findCommandById(Long id);
}
