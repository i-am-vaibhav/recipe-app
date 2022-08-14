package com.anvl.recipe.converters;

import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureToCommandConverterTest {

    @InjectMocks
    private UnitOfMeasureToCommandConverter unitOfMeasureToCommandConverter;

    @Test
    void convertNull() {
        UnitOfMeasureCommand convert = unitOfMeasureToCommandConverter.convert(null);
        assertNull(convert);
    }

    @Test
    void convertEmpty() {
        UnitOfMeasureCommand convert = unitOfMeasureToCommandConverter.convert(new UnitOfMeasure());
        assertNotNull(convert);
    }

    @Test
    void convert(){
        UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setId(1l);
        unitOfMeasure.setUom("Test");
        UnitOfMeasureCommand command=unitOfMeasureToCommandConverter.convert(unitOfMeasure);
        assertEquals(unitOfMeasure.getUom(),command.getUom());
        assertEquals(unitOfMeasure.getId(),command.getId());
    }
}