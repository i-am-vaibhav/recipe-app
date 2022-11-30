package com.anvl.recipe.converters;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Difficulty;
import com.anvl.recipe.model.Notes;
import com.anvl.recipe.model.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeToCommandConverterTest {

    @Mock
    NotesToCommandConverter notesToCommandConverter;
    @Mock
    CategoryToCommandConverter categoryToCommandConverter;
    @Mock
    IngredientToCommandConverter ingredientToCommandConverter;
    @InjectMocks
    RecipeToCommandConverter recipeToCommandConverter;
    @Test
    void convertEmpty() {
        Recipe model = new Recipe();
        RecipeCommand convert = recipeToCommandConverter.convert(model);
        assertNotNull(convert);
    }


    @Test
    void convertNull() {
        RecipeCommand convert = recipeToCommandConverter.convert(null);
        assertNull(convert);
    }
    @Test
    void convert() {
        Recipe model = new Recipe();
        model.setId(1l);
        model.setDescription("DES");
        model.setSource("SRC");
        model.setServings(2);
        model.setDifficulty(Difficulty.MODERATE);
        model.setDirections("DIR");
        model.setNote(new Notes());
        model.setIngredients(new HashSet<>());
        model.setIngredients(new HashSet<>());
        RecipeCommand convert = recipeToCommandConverter.convert(model);
        assertNotNull(convert);
        assertEquals(model.getServings(),model.getServings());
        assertEquals(model.getId(),convert.getId());
        assertEquals(model.getDifficulty(),Difficulty.valueOf(convert.getDifficulty()));
    }
}