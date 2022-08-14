package com.anvl.recipe.converters;

import com.anvl.recipe.commands.CategoryCommand;
import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.model.Category;
import com.anvl.recipe.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToCategoryConverter implements Converter<CategoryCommand, Category> {

    @Nullable
    @Synchronized
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }
        final Category model = new Category();
        model.setId(source.getId());
        model.setDescription(source.getDescription());
        return model;
    }
}
