package com.anvl.recipe.converters;

import com.anvl.recipe.commands.NotesCommand;
import com.anvl.recipe.model.Notes;
import com.anvl.recipe.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToNotesConverter implements Converter<NotesCommand, Notes> {

    @Nullable
    @Synchronized
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) {
            return null;
        }
        final Notes model = new Notes();
        model.setId(source.getId());
        model.setRecipeNotes(source.getRecipeNotes());
        return model;
    }
}
