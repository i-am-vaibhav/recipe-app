package com.anvl.recipe.converters;

import com.anvl.recipe.commands.NotesCommand;
import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Difficulty;
import com.anvl.recipe.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommandToRecipeConverterTest {

    @Mock
    CommandToNotesConverter commandToNotesConverter;
    @Mock
    CommandToCategoryConverter commandToCategoryConverter;
    @Mock
    CommandToIngredientConverter commandToIngredientConverter;
    @InjectMocks
    CommandToRecipeConverter commandToRecipeConverter;

    @BeforeEach
    void setUp() {
    }

    @Test
    void convertEmpty() {
        RecipeCommand command = new RecipeCommand();
        Recipe convert = commandToRecipeConverter.convert(command);
        assertNotNull(convert);
    }


    @Test
    void convertNull() {
        Recipe convert = commandToRecipeConverter.convert(null);
        assertNull(convert);
    }

    @Test
    void convert() {
        RecipeCommand command = new RecipeCommand();
        command.setId(1l);
        command.setDescription("DES");
        command.setSource("SRC");
        command.setServings(2);
        command.setDifficulty("MODERATE");
        command.setDirections("DIR");
        command.setNote(new NotesCommand());
        command.setIngredients(new HashSet<>());
        command.setIngredients(new HashSet<>());
        Recipe convert = commandToRecipeConverter.convert(command);
        assertNotNull(convert);
        assertEquals(command.getServings(),command.getServings());
        assertEquals(command.getId(),convert.getId());
        assertEquals(command.getDifficulty(),convert.getDifficulty().name());
    }
}