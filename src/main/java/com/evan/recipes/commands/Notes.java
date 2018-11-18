package com.evan.recipes.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Notes {
    private Long id;
    private Recipe recipe;
    private String recipeNotes;
}
