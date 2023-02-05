package com.anvl.recipe.controller;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.repository.CategoryRepository;
import com.anvl.recipe.service.ImageService;
import com.anvl.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    private final CategoryRepository categoryRepository;

    public RecipeController(RecipeService recipeService, ImageService imageService, CategoryRepository categoryRepository) {
        this.recipeService = recipeService;
        this.imageService = imageService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping({"/{id}/show"})
    public String showRecipe(Model model, @PathVariable Long id){
        log.debug("show recipe page");
        model.addAttribute("recipe",recipeService.findCommandById(id));
        return "recipe/show";
    }

    @GetMapping
    public String createPage(Model model){
        log.debug("create recipe page");
        model.addAttribute("recipe",new RecipeCommand());
        model.addAttribute("categories",categoryRepository.findAll().stream().collect(Collectors.toList()));
        model.addAttribute("categoriesSelected",new ArrayList<Integer>());
        return "recipe/form";
    }

    @GetMapping("/{id}/update")
    public String updatePage(Model model,@PathVariable Long id){
        log.debug("update recipe page");
        RecipeCommand command = recipeService.findCommandById(id);
        model.addAttribute("recipe",command);
        model.addAttribute("categories",categoryRepository.findAll().stream().collect(Collectors.toList()));
        model.addAttribute("categoriesSelected",command.getCategories().stream().map(a->a.getId()).collect(Collectors.toList()));
        return "recipe/form";
    }

    @PostMapping
    public String createOrUpdate(Model model, @ModelAttribute RecipeCommand recipe){
        log.debug("create or update recipe");
        RecipeCommand save = recipeService.save(recipe);
        return "redirect:/recipes/"+save.getId()+"/show";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable Long id){
        log.debug("delete recipe");
        Optional<RecipeCommand> deletedRecipe = recipeService.deleteById(id);
        return "redirect:/";
    }

}