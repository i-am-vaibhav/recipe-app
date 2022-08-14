package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.converters.CommandToRecipeConverter;
import com.anvl.recipe.converters.RecipeToCommandConverter;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RecipeServiceImplT {

    @Autowired
    private  RecipeRepository recipeRepository;

    @Autowired
    private  RecipeToCommandConverter recipeToCommandConverter;

    @Autowired
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    void save() {
        List<Recipe> recipes = recipeRepository.findAll();
        Recipe recipe = recipes.get(0);
        RecipeCommand command = recipeToCommandConverter.convert(recipe);

        command.setDescription("Superman");
        RecipeCommand  savedRecipe = recipeService.save(command);

        assertEquals(savedRecipe.getId(),command.getId());
        assertEquals("Superman",command.getDescription());
        assertEquals(recipe.getCategories().size(),command.getCategories().size());
        assertEquals(recipe.getIngredients().size(),command.getIngredients().size());

    }
}