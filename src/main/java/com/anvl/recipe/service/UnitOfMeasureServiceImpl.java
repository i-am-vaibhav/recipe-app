package com.anvl.recipe.service;

import com.anvl.recipe.commands.UnitOfMeasureCommand;
import com.anvl.recipe.converters.UnitOfMeasureToCommandConverter;
import com.anvl.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final UnitOfMeasureToCommandConverter unitOfMeasureToCommandConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToCommandConverter unitOfMeasureToCommandConverter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToCommandConverter = unitOfMeasureToCommandConverter;
    }

    @Override
    public List<UnitOfMeasureCommand> getAllUoms() {
        log.info("UnitOfMeasureServiceImpl getAllUoms called");
        return unitOfMeasureRepository.findAll().stream().map(unitOfMeasureToCommandConverter::convert).collect(Collectors.toList());
    }
}
