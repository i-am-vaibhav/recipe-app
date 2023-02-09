package com.anvl.recipe.controller;

import com.anvl.recipe.commands.RecipeCommand;
import com.anvl.recipe.exceptions.NotFoundException;
import com.anvl.recipe.service.ImageService;
import com.anvl.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final RecipeService recipeService;

    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipes/{id}/images")
    public String imageUpload(@PathVariable Long id, Model model) throws NotFoundException {
        RecipeCommand command = recipeService.findCommandById(id);
        model.addAttribute("recipe",command);
        return "recipe/recipeImgUpload";
    }

    @PostMapping("/recipes/{id}/images")
    public String imageUpload(@PathVariable Long id, @RequestParam MultipartFile image) throws Exception {
        RecipeCommand command = imageService.uploadImage(image,id);
        return "redirect:/recipes/"+id+"/show";
    }

    @GetMapping("/recipes/{id}/readImage")
    public void readImage(@PathVariable Long id, HttpServletResponse response) throws NotFoundException {
        RecipeCommand command = recipeService.findCommandById(id);

        Byte[] bytes = command.getImage();

        response.setContentType("image/jpeg");
        byte[] byteImg = new byte[bytes.length];
        int i = 0;
        for (byte b: bytes) {
            byteImg[i++]=b;
        }
        InputStream inputStream = new ByteArrayInputStream(byteImg);
        try {
            IOUtils.copy(inputStream,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
