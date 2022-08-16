package com.anvl.recipe.controller;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createPage() throws Exception {
            MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

            mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.view().name("recipe/form"));
    }

    @Test
    void updatePage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"+1+"/update/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("recipe/form"));
    }

    @Test
    void showRecipe() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"+1+"/show/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
    }

    @Test
    void showRecipeJunit() throws Exception {
        Recipe recipe =new Recipe();
        recipe.setId(1l);
        Mockito.when(recipeService.findById(1l)).thenReturn(recipe);

        ArgumentCaptor<Recipe> setArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        String str = recipeController.showRecipe(model,1l);
        assertEquals(str,"recipe/show");
        Mockito.verify(recipeService,Mockito.times(1)).findById(any());
        Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipe"),setArgumentCaptor.capture());
        Recipe recipeCaptured = setArgumentCaptor.getValue();
        assertNotNull(recipeCaptured);
        assertEquals(1l,recipeCaptured.getId());
    }

    @Test
    void createPageJunit() throws Exception {
        RecipeCommand recipe =new RecipeCommand();

        ArgumentCaptor<RecipeCommand> setArgumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);

        String str = recipeController.createPage(model);
        assertEquals(str,"recipe/form");
        Mockito.verifyNoInteractions(recipeService);
        Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipe"),setArgumentCaptor.capture());
        RecipeCommand recipeCaptured = setArgumentCaptor.getValue();
        assertNotNull(recipeCaptured);
        assertNull(recipeCaptured.getId());
    }

    @Test
    void updatePageJunit() throws Exception {
        RecipeCommand recipe =new RecipeCommand();
        recipe.setId(1l);
        Mockito.when(recipeService.findCommandById(1l)).thenReturn(recipe);

        ArgumentCaptor<RecipeCommand> setArgumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);

        String str = recipeController.updatePage(model,1l);
        assertEquals(str,"recipe/form");
        Mockito.verify(recipeService,Mockito.times(1)).findCommandById(any());
        Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipe"),setArgumentCaptor.capture());
        RecipeCommand recipeCaptured = setArgumentCaptor.getValue();
        assertNotNull(recipeCaptured);
        assertEquals(1l,recipeCaptured.getId());
    }
}