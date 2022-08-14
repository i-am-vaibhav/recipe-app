package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.converters.CommandToRecipeConverter;
import com.anvl.recipe.converters.RecipeToCommandConverter;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return recipeToCommandConverter.convert(recipeRepository.save(commandToRecipeConverter.convert(recipe)));
    }
}
