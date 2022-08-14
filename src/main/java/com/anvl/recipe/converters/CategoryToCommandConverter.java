package com.anvl.recipe.converters;

import com.anvl.recipe.commands.CategoryCommand;
import com.anvl.recipe.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCommandConverter implements Converter<Category, CategoryCommand> {

    @Nullable
    @Synchronized
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }
        final CategoryCommand command = new CategoryCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        return command;
    }
}
