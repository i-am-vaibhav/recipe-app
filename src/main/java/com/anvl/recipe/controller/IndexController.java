package com.anvl.recipe.controller;

import com.anvl.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/","","index","index.html"})
    public String indexPage(Model model){
        log.debug("index page");
        model.addAttribute("recipes",recipeService.getAllRecipes());
        return "index";
    }

    /*
    * * * Request methods * *
    *
    * Safe Methods :- GET,HEAD,TRACE,OPTIONS
    *
    * Idempotent :- A quality of an action such that repetitions
    * of the action have no further effect on the outcome
    *
    * Idempotent Methods : PUT,DELETE and "Safe methods" are also idempotent
    *
    * Non-Idempotent Methods : POST
    *
    * * Common HTTP status codes *
    *
    * 200, 201, 204
    *
    * 301 moved permanently
    *
    * 400 bad request, 401 not authorized, 403 forbidden, 404 not found
    *
    * 500, 503
    *
    * */

}
