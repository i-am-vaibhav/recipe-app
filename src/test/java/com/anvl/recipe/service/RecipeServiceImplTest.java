package com.anvl.recipe.service;

import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);
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
        assertEquals(recipeSet,realRes);
        Mockito.verify(recipeRepository,Mockito.times(1)).findAll();
    }
}