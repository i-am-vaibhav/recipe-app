package com.anvl.recipe.converters;

import com.anvl.recipe.commands.CategoryCommand;
import com.anvl.recipe.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandToCategoryConverterTest {

    CommandToCategoryConverter  commandToCategoryConverter=new CommandToCategoryConverter();

    @Test
    void convertNull() {
        assertNull(commandToCategoryConverter.convert(null));
    }
    @Test
    void convertEmpty() {
        assertNotNull(commandToCategoryConverter.convert(new CategoryCommand()));
    }
    @Test
    void convert() {
        CategoryCommand command=new CategoryCommand();
        command.setId(1l);
        command.setDescription("test");

        Category category=commandToCategoryConverter.convert(command);

        assertNotNull(category);
        assertEquals(category.getId(),command.getId());
        assertEquals(category.getDescription(),command.getDescription());
    }
}