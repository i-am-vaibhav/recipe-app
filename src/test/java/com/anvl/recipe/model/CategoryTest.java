package com.anvl.recipe.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private static Category category;

    @BeforeAll
    static void setUp(){
        category = new Category();
    }
    
    @Test
    void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        category.setRecipes(recipes);
        assertEquals(recipes,category.getRecipes());
    }

    @Test
    void getId() {
        Long longVal = 5l;
        category.setId(longVal);
        assertEquals(longVal,category.getId());
    }

    @Test
    void getDescription() {
        String str = "des";
        category.setDescription(str);
        assertEquals(str,category.getDescription());
    }
}