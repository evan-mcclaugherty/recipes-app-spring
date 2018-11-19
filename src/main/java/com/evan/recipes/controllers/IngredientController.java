package com.evan.recipes.controllers;

import com.evan.recipes.services.IngredientService;
import com.evan.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/recipe/ingredients")
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public String getIngredients(@PathVariable String id, Model model) {
        log.info("GETTING LIST OF INGREDIENTS!");
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredients/list";
    }

    @GetMapping("/show/{recipeId}/{ingredientId}")
    public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.info("GETTING INGREDIENT FOR : " + recipeId + " / " + ingredientId);
        model.addAttribute("ingredient", ingredientService.findByRecipeIdandId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredients/show";
    }
}
