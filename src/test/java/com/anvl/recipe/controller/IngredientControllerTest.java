package com.anvl.recipe.controller;

import com.anvl.recipe.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

public class IngredientControllerTest {

    private IngredientController ingredientController=null;

    @Mock
    private RecipeServiceImpl recipeService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientController = new IngredientController(recipeService);
    }

    @Test
    public void showIngredientByRecipeIdTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"));
    }


}
