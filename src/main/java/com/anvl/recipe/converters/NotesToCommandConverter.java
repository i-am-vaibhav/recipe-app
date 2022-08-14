package com.anvl.recipe.converters;

import com.anvl.recipe.commands.NotesCommand;
import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.Notes;
import com.anvl.recipe.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToCommandConverter implements Converter<Notes, NotesCommand> {

    @Nullable
    @Synchronized
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }
        final NotesCommand command = new NotesCommand();
        command.setId(source.getId());
        command.setRecipeNotes(source.getRecipeNotes());
        return command;
    }
}
