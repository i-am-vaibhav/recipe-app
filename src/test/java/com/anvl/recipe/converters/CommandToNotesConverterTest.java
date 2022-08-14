package com.anvl.recipe.converters;

import com.anvl.recipe.commands.NotesCommand;
import com.anvl.recipe.model.Notes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommandToNotesConverterTest {

    @InjectMocks
    CommandToNotesConverter commandToNotesConverter;


    @Test
    void convertNull() {
        Notes convert = commandToNotesConverter.convert(null);
        assertNull(convert);
    }

    @Test
    void convertEmpty() {
        Notes convert = commandToNotesConverter.convert(new NotesCommand());
        assertNotNull(convert);
    }

    @Test
    void convert() {
        NotesCommand command=new NotesCommand();
        command.setId(1l);
        command.setRecipeNotes("Test");
        Notes notes=commandToNotesConverter.convert(command);
        assertEquals(command.getRecipeNotes(),notes.getRecipeNotes());
        assertEquals(command.getId(),notes.getId());
    }
}