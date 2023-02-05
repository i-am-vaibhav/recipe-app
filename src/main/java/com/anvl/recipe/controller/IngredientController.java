package com.anvl.recipe.controller;

import com.anvl.recipe.commands.IngredientCommand;
import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.service.IngredientService;
import com.anvl.recipe.service.RecipeService;
import com.anvl.recipe.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipes/{id}/ingredients")
    public String showIngredientsByRecipeId(@PathVariable Long id, Model model){
        log.debug("/recipes/{}/ingredients called",id);
        RecipeCommand commandById = recipeService.findCommandById(id);
        model.addAttribute("recipe",commandById);
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipes/{id}/ingredients/{ingredientId}/delete")
    public String deleteIngredientsByRecipeId(@PathVariable Long id,@PathVariable Long ingredientId, Model model){
        log.debug("/recipes/{}/ingredients/{}/delete called",id,ingredientId);
        ingredientService.deleteById(ingredientId);
        return "redirect:/recipes/"+id+"/ingredients";
    }

    @GetMapping("/recipes/{id}/ingredients/{ingredientId}/show")
    public String showIngredientByRecipeId(@PathVariable Long id,@PathVariable Long ingredientId, Model model){
        log.debug("/recipes/{}/ingredients/{}/show called",id,ingredientId);
        IngredientCommand commandById = ingredientService.findIngredient(id,ingredientId);
        model.addAttribute("ingredient",commandById);
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipes/{id}/ingredients/{ingredientId}/update")
    public String updateIngredientByRecipeId(@PathVariable Long id,@PathVariable Long ingredientId, Model model){
        log.debug("/recipes/{}/ingredients/{}/update called",id,ingredientId);
        IngredientCommand commandById = ingredientService.findIngredient(id,ingredientId);
        model.addAttribute("ingredient",commandById);
        model.addAttribute("uoms",unitOfMeasureService.getAllUoms());
        return "recipe/ingredient/form";
    }


    @GetMapping("/recipes/{id}/ingredients/create")
    public String updateIngredientByRecipeId(@PathVariable Long id, Model model){
        log.debug("/recipes/{}/ingredients/create called",id);
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setRecipeId(id);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uoms",unitOfMeasureService.getAllUoms());
        return "recipe/ingredient/form";
    }

    @PostMapping("/recipes/{id}/ingredients")
    public String saveIngredientByRecipeId(@PathVariable Long id,  @ModelAttribute IngredientCommand ingredient) throws Exception {
        log.debug("/recipes/{}/ingredients save called",id);
        ingredient.setRecipeId(id);
        ingredientService.save(ingredient);
        return "redirect:/recipes/"+id+"/ingredients";
    }


}
