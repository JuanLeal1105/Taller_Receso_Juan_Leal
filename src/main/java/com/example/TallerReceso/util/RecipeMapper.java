package com.example.TallerReceso.util;

import com.example.TallerReceso.Dto.recipeDTO.RecipeDTO;
import com.example.TallerReceso.Dto.recipeDTO.RecipeResponseDTO;
import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Model.document.Recipe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RecipeMapper {
    public RecipeResponseDTO mapToRecipeResponseDTO(Recipe recipe) {
        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setUserName(recipe.getUserName());
        recipeResponseDTO.setRecipeName(recipe.getRecipeName());
        recipeResponseDTO.setRecipeDescription(recipe.getRecipeDescription());
        recipeResponseDTO.setSteps(recipe.getSteps());
        recipeResponseDTO.setIngredients(recipe.getIngredients());
        return recipeResponseDTO;
    }
}
