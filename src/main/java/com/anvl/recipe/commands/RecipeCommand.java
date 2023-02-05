package com.anvl.recipe.commands;

import com.anvl.recipe.model.Category;
import com.anvl.recipe.model.Difficulty;
import com.anvl.recipe.model.Ingredient;
import com.anvl.recipe.model.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private String difficulty;

    private Byte[] image;

    private List<CategoryCommand> categories;

    private Set<IngredientCommand> ingredients;

    private NotesCommand note;

}
