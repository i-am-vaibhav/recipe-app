package com.anvl.recipe.converters;

import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToCommandConverter implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Nullable
    @Synchronized
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null) {
            return null;
        }
        final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(source.getId());
        command.setUom(source.getUom());
        return command;
    }
}
