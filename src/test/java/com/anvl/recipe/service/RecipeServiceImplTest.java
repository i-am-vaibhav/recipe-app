package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.converters.CommandToRecipeConverter;
import com.anvl.recipe.converters.RecipeToCommandConverter;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.CategoryRepository;
import com.anvl.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    CommandToRecipeConverter commandToRecipeConverter;

    @Mock
    private RecipeToCommandConverter recipeToCommandConverter;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, categoryRepository, commandToRecipeConverter, recipeToCommandConverter);
    }

    @Test
    void getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        Set<Recipe> recipeSet = new HashSet<>();
        Recipe recipe = new Recipe();
        recipes.add(recipe);
        recipeSet.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> realRes = recipeService.getAllRecipes();
        assertEquals(recipeSet, realRes);
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Mockito.when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));

        Recipe realRecipe = recipeService.findById(1l);
        assertNotNull(realRecipe);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(any());

    }

    @Test
    void getRecipeCommandByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Mockito.when(recipeRepository.findById(1l)).thenReturn(Optional.of(recipe));
        RecipeCommand command = new RecipeCommand();
        command.setId(1l);
        Mockito.when(recipeToCommandConverter.convert(any())).thenReturn(command);

        RecipeCommand realRecipe = recipeService.findCommandById(1l);
        assertNotNull(realRecipe);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(any());

        Mockito.verify(recipeToCommandConverter, Mockito.times(1)).convert(any());

    }
}