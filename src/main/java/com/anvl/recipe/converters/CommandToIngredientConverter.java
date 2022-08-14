package com.anvl.recipe.converters;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.model.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToIngredientConverter implements Converter<IngredientCommand, Ingredient> {

    private final CommandToUnitOfMeasureConverter commandToUnitOfMeasureConverter;

    public CommandToIngredientConverter(CommandToUnitOfMeasureConverter commandToUnitOfMeasureConverter) {
        this.commandToUnitOfMeasureConverter = commandToUnitOfMeasureConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        final Ingredient model = new Ingredient();
        model.setId(source.getId());
        model.setDescription(source.getDescription());
        model.setAmount(source.getAmount());
        model.setUnitOfMeasure(commandToUnitOfMeasureConverter.convert(source.getUnitOfMeasure()));
        return model;
    }
}
