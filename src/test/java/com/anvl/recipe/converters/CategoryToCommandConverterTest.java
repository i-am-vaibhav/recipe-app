package com.anvl.recipe.converters;

import com.anvl.recipe.commands.CategoryCommand;
import com.anvl.recipe.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCommandConverterTest {

    CategoryToCommandConverter  categoryToCommandConverter=new CategoryToCommandConverter();

    @Test
    void convertNull() {
        assertNull(categoryToCommandConverter.convert(null));
    }
    @Test
    void convertEmpty() {
        assertNotNull(categoryToCommandConverter.convert(new Category()));
    }
    @Test
    void convert() {
        Category command=new Category();
        command.setId(1l);
        command.setDescription("test");

        CategoryCommand category=categoryToCommandConverter.convert(command);

        assertNotNull(category);
        assertEquals(category.getId(),command.getId());
        assertEquals(category.getDescription(),command.getDescription());
    }
}