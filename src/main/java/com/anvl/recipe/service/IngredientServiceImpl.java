package com.anvl.recipe.service;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.converters.CommandToIngredientConverter;
import com.anvl.recipe.converters.IngredientToCommandConverter;
import com.anvl.recipe.model.Ingredient;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.model.UnitOfMeasure;
import com.anvl.recipe.repository.IngredientRepository;
import com.anvl.recipe.repository.RecipeRepository;
import com.anvl.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements  IngredientService{

    private final RecipeRepository recipeRepository;

    private final IngredientToCommandConverter ingredientToCommandConverter;

    private final CommandToIngredientConverter commandToIngredientConverter;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToCommandConverter ingredientToCommandConverter, CommandToIngredientConverter commandToIngredientConverter, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToCommandConverter = ingredientToCommandConverter;
        this.commandToIngredientConverter = commandToIngredientConverter;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
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

    @Override
    public void save(IngredientCommand ingredient) throws Exception {
        Ingredient ingredientToSave = commandToIngredientConverter.convert(ingredient);
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findById(ingredientToSave.getUnitOfMeasure().getId()).orElse(null);
        if (unitOfMeasure==null){
            throw new Exception("Failed to update/create ingredient, unit of measure is null");
        }
        ingredientToSave.setUnitOfMeasure(unitOfMeasure);
        ingredientToSave.setRecipe(recipeRepository.findById(ingredient.getRecipeId()).get());
        ingredientRepository.save(ingredientToSave);
    }

    @Override
    public void deleteById(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
