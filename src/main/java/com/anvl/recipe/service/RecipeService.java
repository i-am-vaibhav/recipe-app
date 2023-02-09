package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.exceptions.NotFoundException;
import com.anvl.recipe.model.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Recipe findById(Long id) throws NotFoundException;

    RecipeCommand save(RecipeCommand recipe) throws NotFoundException;

    RecipeCommand findCommandById(Long id) throws NotFoundException;

    Optional<RecipeCommand> deleteById(Long id);
}
