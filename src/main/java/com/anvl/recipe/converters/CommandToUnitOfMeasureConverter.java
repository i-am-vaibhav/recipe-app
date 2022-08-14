package com.anvl.recipe.converters;

import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToUnitOfMeasureConverter implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }
        final UnitOfMeasure model = new UnitOfMeasure();
        model.setId(source.getId());
        model.setUom(source.getUom());
        return model;
    }
}
