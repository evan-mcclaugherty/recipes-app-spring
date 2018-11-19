package com.evan.recipes.controllers;

import com.evan.recipes.commands.IngredientCommand;
import com.evan.recipes.commands.RecipeCommand;
import com.evan.recipes.commands.UnitOfMeasureCommand;
import com.evan.recipes.services.IngredientService;
import com.evan.recipes.services.RecipeService;
import com.evan.recipes.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/recipe/ingredients")
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping("/new/{recipeId}")
    public String newRecipe(@PathVariable String recipeId, Model model) {

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredients/ingredientform";
    }

    @GetMapping("/update/{recipeId}/{ingredientId}")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdandId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientform";
    }

    @PostMapping("/{recipeId}")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/ingredients/show/" + savedCommand.getRecipeId() + "/" + savedCommand.getId();
    }

    @GetMapping("/delete/{recipeId}/{id}")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id) {

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/ingredients/" + recipeId;
    }
}
