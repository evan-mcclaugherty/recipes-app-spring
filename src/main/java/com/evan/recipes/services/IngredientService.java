package com.evan.recipes.services;

import com.evan.recipes.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdandId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long idToDelete);
}
