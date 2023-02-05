package com.anvl.recipe.service;

import com.anvl.recipe.commands.UnitOfMeasureCommand;

import java.util.List;

public interface UnitOfMeasureService {

    List<UnitOfMeasureCommand> getAllUoms();

}
