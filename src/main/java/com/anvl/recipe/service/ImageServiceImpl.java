package com.anvl.recipe.service;


import com.anvl.recipe.commands.RecipeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeService recipeService;

    public ImageServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public RecipeCommand uploadImage(MultipartFile file, Long id) throws Exception {
        RecipeCommand command =recipeService.findCommandById(id);
        try {
            byte[] bytes = file.getBytes();
            Byte[] bytes1 = new Byte[bytes.length];
            for (int i = 0;i<bytes.length;i++){
                bytes1[i] = bytes[i];
            }
            command.setImage(bytes1);
        } catch (IOException e) {
            throw new Exception("Image not found");
        }
        return recipeService.save(command);
    }
}
