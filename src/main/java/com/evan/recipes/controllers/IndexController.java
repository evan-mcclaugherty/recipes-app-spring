package com.evan.recipes.controllers;

import com.evan.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping({"", "/", "index"})
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String getIndexPage(Model model) {
        log.info("Getting Index Page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }


}
