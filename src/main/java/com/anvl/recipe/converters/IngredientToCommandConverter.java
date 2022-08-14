package com.anvl.recipe.converters;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToCommandConverter implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToCommandConverter unitOfMeasureToCommandConverter;

    public IngredientToCommandConverter(UnitOfMeasureToCommandConverter unitOfMeasureToCommandConverter) {
        this.unitOfMeasureToCommandConverter = unitOfMeasureToCommandConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }
        final IngredientCommand command = new IngredientCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setAmount(source.getAmount());
        command.setUnitOfMeasure(unitOfMeasureToCommandConverter.convert(source.getUnitOfMeasure()));
        return command;
    }
}
