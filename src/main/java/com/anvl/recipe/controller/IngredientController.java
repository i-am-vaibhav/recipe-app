package com.anvl.recipe.controller;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/{id}/ingredients")
    public String showIngredientByRecipeId(@PathVariable Long id, Model model){
        log.debug("/recipes/"+id+"/ingredients called");
        RecipeCommand commandById = recipeService.findCommandById(id);
        model.addAttribute("recipe",commandById);
        return "recipe/ingredient/list";
    }

}
