package com.example.TallerReceso.Dto.recipeDTO;

import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.util.KitchenRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class RecipeDTO {
    private String userId;
    private String recipeName;
    private String recipeDescription;

    private HashMap<String, ArrayList<Object>> details;
}
