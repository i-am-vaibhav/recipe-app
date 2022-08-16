package com.anvl.recipe.controller;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/{id}/show"})
    public String showRecipe(Model model, @PathVariable Long id){
        System.out.println("show recipe page");
        model.addAttribute("recipe",recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping
    public String createPage(Model model){
        System.out.println("create recipe page");
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/form";
    }

    @GetMapping("/{id}/update")
    public String updatePage(Model model,@PathVariable Long id){
        System.out.println("update recipe page");
        model.addAttribute("recipe",recipeService.findCommandById(id));
        return "recipe/form";
    }

    @PostMapping
    public String createOrUpdate(Model model, @ModelAttribute RecipeCommand recipe){
        System.out.println("create or update recipe");
        RecipeCommand save = recipeService.save(recipe);
        return "redirect:/recipes/"+save.getId()+"/show";
    }

}