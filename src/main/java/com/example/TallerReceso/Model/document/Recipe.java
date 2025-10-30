package com.example.TallerReceso.Model.document;

import com.example.TallerReceso.util.KitchenRole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document(collection = "Recipes")
public class Recipe {
    @Id
    private String id;

    private String recipeName;
    private String recipeDescription;
    private String season;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;

    private String userId;
    private String userName;
    private KitchenRole kitchenRole;
}
