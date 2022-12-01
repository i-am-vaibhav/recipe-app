package com.anvl.recipe.service;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.converters.IngredientToCommandConverter;
import com.anvl.recipe.model.Ingredient;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientToCommandConverter ingredientToCommandConverter;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findIngredient() {
        Set<Ingredient> ingredients =new HashSet<>();
        Ingredient add = new Ingredient();
        add.setId(2l);
        ingredients.add(add);
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        recipe.setIngredients(ingredients);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1l)).thenReturn(recipeOptional);
        IngredientCommand cmd = new IngredientCommand();
        cmd.setId(2l);
        Mockito.when(ingredientToCommandConverter.convert(ArgumentMatchers.any())).thenReturn(cmd);
        IngredientCommand ingredient = ingredientService.findIngredient(1l, 2l);
        Assertions.assertNotNull(ingredient);
        Assertions.assertEquals(2l,ingredient.getId());
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(ArgumentMatchers.any());
        Mockito.verify(ingredientToCommandConverter, Mockito.times(1)).convert(ArgumentMatchers.any());
    }

    @Test
    void findIngredientIfIngredientAreNull() {
        Set<Ingredient> ingredients =new HashSet<>();
        Ingredient add = new Ingredient();
        add.setId(2l);
        ingredients.add(add);
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1l)).thenReturn(recipeOptional);
        IngredientCommand ingredient = ingredientService.findIngredient(1l, 2l);
        Assertions.assertNull(ingredient);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(ArgumentMatchers.any());
    }
}