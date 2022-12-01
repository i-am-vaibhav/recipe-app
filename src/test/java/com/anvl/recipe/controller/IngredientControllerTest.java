package com.anvl.recipe.controller;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.service.IngredientService;
import com.anvl.recipe.service.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;

public class IngredientControllerTest {

    private IngredientController ingredientController=null;

    @Mock
    private RecipeServiceImpl recipeService;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ingredientController = new IngredientController(recipeService, ingredientService);
    }

    @Test
    public void showIngredientsByRecipeIdTest() throws Exception {
        RecipeCommand recipe =new RecipeCommand();
        recipe.setId(1l);
        Mockito.when(recipeService.findCommandById(1l)).thenReturn(recipe);
        ArgumentCaptor<RecipeCommand> recipeArgumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);

        String url = ingredientController.showIngredientByRecipeId(1l,model);
        Assertions.assertEquals(url,"recipe/ingredient/list");
        Mockito.verify(recipeService,Mockito.times(1)).findCommandById(any());
        Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipe"),recipeArgumentCaptor.capture());

        RecipeCommand capture = recipeArgumentCaptor.getValue();
        Assertions.assertNotNull(capture);
        Assertions.assertNotNull(capture.getId());
    }

    @Test
    public void showIngredientDetailsByRecipeIdAndIngredientIdTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/1/ingredients/20/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/show"));
    }


}
