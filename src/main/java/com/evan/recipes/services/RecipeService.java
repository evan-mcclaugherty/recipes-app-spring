package com.evan.recipes.services;

import com.evan.recipes.commands.RecipeCommand;
import com.evan.recipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);

    void deleteRecipeById(Long id);
}
