package com.anvl.recipe.controller;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.service.IngredientService;
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


    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipes/{id}/ingredients")
    public String showIngredientByRecipeId(@PathVariable Long id, Model model){
        log.debug("/recipes/{}/ingredients called",id);
        RecipeCommand commandById = recipeService.findCommandById(id);
        model.addAttribute("recipe",commandById);
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipes/{id}/ingredients/{ingredientId}/show")
    public String showIngredientByRecipeId(@PathVariable Long id,@PathVariable Long ingredientId, Model model){
        log.debug("/recipes/{}/ingredients/{}/show called",id,ingredientId);
        IngredientCommand commandById = ingredientService.findIngredient(id,ingredientId);
        model.addAttribute("ingredient",commandById);
        return "recipe/ingredient/show";
    }


}
