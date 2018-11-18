package com.evan.recipes.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Category {
    private Long id;
    private Set<Recipe> recipes;
    private String description;

}
