package com.anvl.recipe.exceptions;

import com.anvl.recipe.converters.CommandToRecipeConverter;
import com.anvl.recipe.converters.RecipeToCommandConverter;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.CategoryRepository;
import com.anvl.recipe.repository.RecipeRepository;
import com.anvl.recipe.service.RecipeService;
import com.anvl.recipe.service.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotFoundExceptionTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CommandToRecipeConverter commandToRecipeConverter;
    @Mock
    private RecipeToCommandConverter recipeToCommandConverter;

    private RecipeService recipeService;

    @BeforeEach
    public void setup() {
        recipeService = new RecipeServiceImpl(recipeRepository, categoryRepository, commandToRecipeConverter, recipeToCommandConverter);
        MockitoAnnotations.openMocks(recipeService);
    }

    @Test
    public void testNotFoundException() throws NotFoundException {
        Optional<Recipe> recipe = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipe);
        Assertions.assertThrows(NotFoundException.class, () -> recipeService.findById(1l), "Recipe Not Found");
    }

}