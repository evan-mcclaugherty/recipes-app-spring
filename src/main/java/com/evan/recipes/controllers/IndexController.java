package com.evan.recipes.controllers;

import com.evan.recipes.domain.Category;
import com.evan.recipes.domain.UnitOfMeasure;
import com.evan.recipes.repositories.CategoryRepository;
import com.evan.recipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping({"", "/", "index"})
    public String getIndexPage() {
        Optional<Category> category = categoryRepository.findByDescription("Italian");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");
        System.out.println("Category: " + category.get().getId() + ") " + category.get().getDescription());
        System.out.println("Unit Of Measure: " + unitOfMeasure.get().getId() + ") " + unitOfMeasure.get().getDescription());
        return "index";
    }
}
