package com.anvl.recipe.repository;

import com.anvl.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UnitOfMeasureRepositoryITest {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUom() {
        Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByUom("Cup");
        assertEquals("Cup",cup.get().getUom());
    }

    @Test
    void findByUomTeaspoon() {
        Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByUom("Teaspoon");
        assertEquals("Teaspoon",cup.get().getUom());
    }
}