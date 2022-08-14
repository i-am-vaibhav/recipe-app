package com.anvl.recipe.converters;

import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommandToUnitOfMeasureConverterTest {

    @InjectMocks
    private CommandToUnitOfMeasureConverter commandToUnitOfMeasureConverter;

    @Test
    void convertNull() {
        UnitOfMeasure convert = commandToUnitOfMeasureConverter.convert(null);
        assertNull(convert);
    }

    @Test
    void convertEmpty() {
        UnitOfMeasure convert = commandToUnitOfMeasureConverter.convert(new UnitOfMeasureCommand());
        assertNotNull(convert);
    }

    @Test
    void convert() {
        UnitOfMeasureCommand command=new UnitOfMeasureCommand();
        command.setId(1l);
        command.setUom("Test");
        UnitOfMeasure unitOfMeasure=commandToUnitOfMeasureConverter.convert(command);
        assertEquals(unitOfMeasure.getUom(),command.getUom());
        assertEquals(unitOfMeasure.getId(),command.getId());

    }
}