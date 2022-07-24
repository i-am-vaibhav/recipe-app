package com.anvl.recipe.controller;

import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    private IndexController indexController ;

    @Mock
    private RecipeServiceImpl recipeService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    void indexPageMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                        .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("index"));

    }

    @Test
    void indexPage() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());
        Mockito.when(recipeService.getAllRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);

        String str = indexController.indexPage(model);
        assertEquals(str,"index");
        Mockito.verify(recipeService,Mockito.times(1)).getAllRecipes();
        Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipes"),setArgumentCaptor.capture());
        Set<Recipe> recipeSet = setArgumentCaptor.getValue();
        assertEquals(2,recipeSet.size());
    }
}