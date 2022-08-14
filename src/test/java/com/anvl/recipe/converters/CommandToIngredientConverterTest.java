package com.anvl.recipe.converters;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CommandToIngredientConverterTest {

    CommandToIngredientConverter commandToIngredientConverter;

    @BeforeEach
    void setUp() {
        commandToIngredientConverter = new CommandToIngredientConverter(new CommandToUnitOfMeasureConverter());
    }

    @Test
    void convertNull() {
        Ingredient convert = commandToIngredientConverter.convert(null);
        assertNull(convert);
    }

    @Test
    void convertEmpty() {
        Ingredient convert = commandToIngredientConverter.convert(new IngredientCommand());
        assertNotNull(convert);
    }

    @Test
    void convert() {
        IngredientCommand command = new IngredientCommand();
        command.setId(1l);
        command.setDescription("Test");
        command.setAmount(BigDecimal.TEN);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(1l);
        command.setUnitOfMeasure(unitOfMeasureCommand);
        Ingredient ingredient = commandToIngredientConverter.convert(command);
        assertEquals(command.getDescription(), ingredient.getDescription());
        assertEquals(command.getId(), ingredient.getId());
        assertEquals(command.getAmount(), ingredient.getAmount());
        assertNotNull(ingredient.getUnitOfMeasure());
    }
}