package com.anvl.recipe.converters;

import com.anvl.recipe.commands.CategoryCommand;
import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Difficulty;
import com.anvl.recipe.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommandToRecipeConverter implements Converter<RecipeCommand, Recipe> {

    private final CommandToCategoryConverter commandToCategoryConverter;

    private final CommandToNotesConverter commandToNotesConverter;

    private final CommandToIngredientConverter commandToIngredientConverter;

    public CommandToRecipeConverter( CommandToCategoryConverter commandToCategoryConverter, CommandToNotesConverter commandToNotesConverter, CommandToIngredientConverter commandToIngredientConverter){
        this.commandToCategoryConverter = commandToCategoryConverter;
        this.commandToNotesConverter = commandToNotesConverter;
        this.commandToIngredientConverter = commandToIngredientConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        final Recipe model = new Recipe();
        model.setId(source.getId());
        model.setDescription(source.getDescription());
        model.setCookTime(source.getCookTime());
        model.setPrepTime(source.getPrepTime());
        if(source.getImage()!=null)
            model.setImage(source.getImage());
        model.setDirections(source.getDirections());
        model.setUrl(source.getUrl());
        model.setServings(source.getServings());
        if(source.getDifficulty()!=null)
            model.setDifficulty(Difficulty.valueOf(source.getDifficulty()));
        model.setSource(source.getSource());

        List<CategoryCommand> categories = source.getCategories();
        if(categories!=null) {
            model.setCategories(categories.stream().map(commandToCategoryConverter::convert).collect(Collectors.toSet()));
        }
        Set<IngredientCommand> ingredients = source.getIngredients();
        if(ingredients!=null) {
            model.setIngredients(ingredients.stream().map(commandToIngredientConverter::convert).collect(Collectors.toSet()));
        }
        model.setNote(commandToNotesConverter.convert(source.getNote()));
        return model;
    }
}
