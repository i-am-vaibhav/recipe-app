package com.anvl.recipe.service;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.converters.CommandToIngredientConverter;
import com.anvl.recipe.converters.IngredientToCommandConverter;
import com.anvl.recipe.model.Ingredient;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements  IngredientService{

    private final RecipeRepository recipeRepository;

    private final IngredientToCommandConverter ingredientToCommandConverter;
    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToCommandConverter ingredientToCommandConverter) {
        this.recipeRepository = recipeRepository;
        this.ingredientToCommandConverter = ingredientToCommandConverter;
    }

    @Override
    public IngredientCommand findIngredient(Long recipeId, Long ingredientId){
        log.info("findIngredient by recipeId {} and ingredientId {}",recipeId,ingredientId);
        Optional<Recipe> recipeById = recipeRepository.findById(recipeId);
        if(!recipeById.isPresent()){
            log.error("findIngredient Ingredient not found");
            return null;
        }
        Recipe recipe = recipeById.get();
        if(recipe.getIngredients()==null){
            log.error("findIngredient Ingredient not found");
            return null;
        }
        Optional<Ingredient> ingredientCommand=recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId() == ingredientId).findFirst();

        if(ingredientCommand.isPresent()){
            log.info("findIngredient Ingredient found");
            return ingredientToCommandConverter.convert(ingredientCommand.get());
        }
        log.error("findIngredient Ingredient not found");
        return null;
    }

}
