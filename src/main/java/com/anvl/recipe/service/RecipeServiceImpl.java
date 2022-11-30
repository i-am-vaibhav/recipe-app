package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.converters.CommandToRecipeConverter;
import com.anvl.recipe.converters.RecipeToCommandConverter;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    private final CommandToRecipeConverter commandToRecipeConverter;

    private final RecipeToCommandConverter recipeToCommandConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, CommandToRecipeConverter commandToRecipeConverter, RecipeToCommandConverter recipeToCommandConverter) {
        this.recipeRepository = recipeRepository;
        this.commandToRecipeConverter = commandToRecipeConverter;
        this.recipeToCommandConverter = recipeToCommandConverter;
    }



    @Override
    public Set<Recipe> getAllRecipes() {
        return recipeRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public RecipeCommand save(RecipeCommand recipe) {
        log.debug("save recipe");
        Recipe convert = commandToRecipeConverter.convert(recipe);
        convert.getNote().setRecipe(convert);
        return recipeToCommandConverter.convert(recipeRepository.save(convert));
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id){
        return recipeToCommandConverter.convert(findById(id));
    }

    @Override
    @Transactional
    public Optional<RecipeCommand> deleteById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return Optional.of(recipeToCommandConverter.convert(recipe.get()));
        }else{
            return Optional.empty();
        }
    }

}
