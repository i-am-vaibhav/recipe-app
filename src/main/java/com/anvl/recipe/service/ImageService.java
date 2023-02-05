package com.anvl.recipe.service;

import com.anvl.recipe.commands.RecipeCommand;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public RecipeCommand uploadImage(MultipartFile file, Long id) throws Exception;

}
