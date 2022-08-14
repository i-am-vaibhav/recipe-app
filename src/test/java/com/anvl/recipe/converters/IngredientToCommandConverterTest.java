package com.anvl.recipe.converters;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.Ingredient;
import com.anvl.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToCommandConverterTest {

    IngredientToCommandConverter ingredientToCommandConverter;

    @BeforeEach
    void setUp() {
        ingredientToCommandConverter = new IngredientToCommandConverter(new UnitOfMeasureToCommandConverter());
    }

    @Test
    void convertNull() {
        IngredientCommand convert = ingredientToCommandConverter.convert(null);
        assertNull(convert);
    }

    @Test
    void convertEmpty() {
        IngredientCommand convert = ingredientToCommandConverter.convert(new Ingredient());
        assertNotNull(convert);
    }

    @Test
    void convert() {
        Ingredient command = new Ingredient();
        command.setId(1l);
        command.setDescription("Test");
        command.setAmount(BigDecimal.TEN);
        UnitOfMeasure unitOfMeasureCommand = new UnitOfMeasure();
        unitOfMeasureCommand.setId(1l);
        command.setUnitOfMeasure(unitOfMeasureCommand);
        IngredientCommand ingredient = ingredientToCommandConverter.convert(command);
        assertEquals(command.getDescription(), ingredient.getDescription());
        assertEquals(command.getId(), ingredient.getId());
        assertEquals(command.getAmount(), ingredient.getAmount());
        assertNotNull(ingredient.getUnitOfMeasure());
    }
}