package com.anvl.recipe.controller;

import antlr.ASTNULLType;
import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.exceptions.NotFoundException;
import com.anvl.recipe.model.Category;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.CategoryRepository;
import com.anvl.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeAll;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    private Model model;

    MockMvc mockMvc = null;
    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void createPage() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.view().name("recipe/form"));
    }

    @Test
    void updatePage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"+11+"/update/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("recipe/form"));
    }

    @Test
    void showRecipe() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"+1+"/show/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
    }
    @Test
    void showRecipeNotFound() throws Exception {
        Mockito.when(recipeService.findCommandById(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"+4+"/show/"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.forwardedUrl("404error"));
    }

    @Test
    void showRecipeNumberFormatException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/test/show/"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.forwardedUrl("400error"));
    }

    @Test
    void showRecipeJunit() throws Exception {
        RecipeCommand recipe =new RecipeCommand();
        recipe.setId(1l);
        Mockito.when(recipeService.findCommandById(1l)).thenReturn(recipe);

        ArgumentCaptor<RecipeCommand> setArgumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);

        String str = recipeController.showRecipe(model,1l);
        assertEquals(str,"recipe/show");
        Mockito.verify(recipeService,Mockito.times(1)).findCommandById(any());
        Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipe"),setArgumentCaptor.capture());
        RecipeCommand recipeCaptured = setArgumentCaptor.getValue();
        assertNotNull(recipeCaptured);
        assertEquals(1l,recipeCaptured.getId());
    }

    @Test
    void createPageJunit() throws Exception {
        RecipeCommand recipe =new RecipeCommand();

        ArgumentCaptor<RecipeCommand> setArgumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);
        Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<Category>());

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
        recipe.setCategories(new ArrayList<>());
        Mockito.when(recipeService.findCommandById(1l)).thenReturn(recipe);
        Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<Category>());

        ArgumentCaptor<RecipeCommand> setArgumentCaptor = ArgumentCaptor.forClass(RecipeCommand.class);

        String str = recipeController.updatePage(model,1l);
        assertEquals(str,"recipe/form");
        Mockito.verify(recipeService,Mockito.times(1)).findCommandById(any());
        Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipe"),setArgumentCaptor.capture());
        RecipeCommand recipeCaptured = setArgumentCaptor.getValue();
        assertNotNull(recipeCaptured);
        assertEquals(1l,recipeCaptured.getId());
    }

    @Test
    void deleteApiJunit() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/"+1+"/delete"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
}