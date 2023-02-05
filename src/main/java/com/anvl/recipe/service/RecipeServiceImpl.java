package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.converters.CommandToRecipeConverter;
import com.anvl.recipe.converters.RecipeToCommandConverter;
import com.anvl.recipe.model.Category;
import com.anvl.recipe.model.Recipe;
import com.anvl.recipe.repository.CategoryRepository;
import com.anvl.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    private final CategoryRepository categoryRepository;

    private final CommandToRecipeConverter commandToRecipeConverter;

    private final RecipeToCommandConverter recipeToCommandConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, CategoryRepository categoryRepository, CommandToRecipeConverter commandToRecipeConverter, RecipeToCommandConverter recipeToCommandConverter) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.commandToRecipeConverter = commandToRecipeConverter;
        this.recipeToCommandConverter = recipeToCommandConverter;
    }



    @Override
    public Set<Recipe> getAllRecipes() {
        return new HashSet<>(recipeRepository.findAll());
    }

    @Override
    public Recipe findById(Long id) {
        if(id==null)
            return null;
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public RecipeCommand save(RecipeCommand recipe) {
        log.debug("save recipe");
        Recipe convert = commandToRecipeConverter.convert(recipe);
        Set<Category> categories = new HashSet<>();
        for (Category category: convert.getCategories() ) {
            Long id = category.getId();
            if(id!=null)
                categories.add(categoryRepository.findById(id).orElse(null));
        }
        assert convert != null;
        convert.setCategories(categories);
        convert.getNote().setRecipe(convert);
        Recipe recipe1 = findById(recipe.getId());
        if(recipe1!=null){
            if(convert.getImage()==null &&recipe1.getImage()!=null)
                 convert.setImage(recipe1.getImage());
        }
        return recipeToCommandConverter.convert(recipeRepository.save(convert));
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id){
        return recipeToCommandConverter.convert(findById(id));
    }

    @Override
    @Transactional
    public Optional<RecipeCommand> deleteById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return Optional.ofNullable(recipeToCommandConverter.convert(recipe.get()));
        }else{
            return Optional.empty();
        }
    }

}
