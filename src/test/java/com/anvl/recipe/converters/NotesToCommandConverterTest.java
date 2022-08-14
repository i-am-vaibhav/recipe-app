package com.anvl.recipe.converters;

import com.anvl.recipe.commands.NotesCommand;
import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.Notes;
import com.anvl.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NotesToCommandConverterTest {

    @InjectMocks
    NotesToCommandConverter notesToCommandConverter;


    @Test
    void convertNull() {
        NotesCommand convert = notesToCommandConverter.convert(null);
        assertNull(convert);
    }

    @Test
    void convertEmpty() {
        NotesCommand convert = notesToCommandConverter.convert(new Notes());
        assertNotNull(convert);
    }

    @Test
    void convert() {
        Notes notes=new Notes();
        notes.setId(1l);
        notes.setRecipeNotes("Test");
        NotesCommand command=notesToCommandConverter.convert(notes);
        assertEquals(notes.getRecipeNotes(),command.getRecipeNotes());
        assertEquals(notes.getId(),command.getId());
    }
}