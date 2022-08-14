package com.anvl.recipe.converters;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Category;
import com.anvl.recipe.model.Ingredient;
import com.anvl.recipe.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeToCommandConverter implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCommandConverter categoryToCommandConverter;

    private final NotesToCommandConverter notesToCommandConverter;

    private final IngredientToCommandConverter ingredientToCommandConverter;

    public RecipeToCommandConverter(CategoryToCommandConverter categoryToCommandConverter, NotesToCommandConverter notesToCommandConverter, IngredientToCommandConverter ingredientToCommandConverter) {
        this.categoryToCommandConverter = categoryToCommandConverter;
        this.notesToCommandConverter = notesToCommandConverter;
        this.ingredientToCommandConverter = ingredientToCommandConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setImage(source.getImage());
        command.setDirections(source.getDirections());
        command.setUrl(source.getUrl());
        command.setServings(source.getServings());
        command.setDifficulty(source.getDifficulty());
        command.setSource(source.getSource());
        Set<Category> categories = source.getCategories();
        if (categories != null)
            command.setCategories(categories.stream().map(categoryToCommandConverter::convert).collect(Collectors.toSet()));

        Set<Ingredient> ingredients = source.getIngredients();
        if (ingredients != null)
            command.setIngredients(ingredients.stream().map(ingredientToCommandConverter::convert).collect(Collectors.toSet()));
        command.setNote(notesToCommandConverter.convert(source.getNote()));
        return command;
    }
}
