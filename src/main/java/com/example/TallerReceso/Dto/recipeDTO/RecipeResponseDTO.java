package com.example.TallerReceso.Dto.recipeDTO;

import com.example.TallerReceso.Model.document.Ingredient;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RecipeResponseDTO {
    private String userName;
    private String recipeName;
    private String recipeDescription;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;
}
