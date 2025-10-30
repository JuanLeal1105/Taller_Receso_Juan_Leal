package com.example.TallerReceso.Model.recipeCreator;

import com.example.TallerReceso.Dto.recipeDTO.RecipeDTO;
import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.Service.ChefService;
import com.example.TallerReceso.Service.KitcherObserverService;
import com.example.TallerReceso.Service.ParticipantService;
import com.example.TallerReceso.util.KitchenRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class RecipeCreator {
    @Autowired
    private ChefService chefService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private KitcherObserverService kitcherObserverService;

    public Recipe createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setId(UUID.randomUUID().toString());
        recipe.setRecipeName(recipeDTO.getRecipeName());
        recipe.setRecipeDescription(recipeDTO.getRecipeDescription());
        recipe.setIngredients((ArrayList<Ingredient>) recipeDTO.getDetails().get("ingredients"));
        recipe.setSteps((ArrayList<String>) recipeDTO.getDetails().get("steps"));
        recipe.setUserId(recipeDTO.getUserId());
        recipe.setUserName(assignUserName(recipeDTO.getUserId()));
        recipe.setKitchenRole(assignRole(recipeDTO.getUserId()));
        recipe.setSeason(recipeDTO.getSeason());
        return recipe;
    }

    private String assignUserName(String userId) {
        try {
            return chefService.findById(userId).getChefName();
        } catch (RuntimeException ignored) {
        }
        try {
            return participantService.findById(userId).getParticipantName();
        } catch (RuntimeException ignored) {
        }
        try {
            return kitcherObserverService.findById(userId).getObserverName();
        } catch (RuntimeException ignored) {
        }
        throw new RuntimeException("User not found");
    }
    private KitchenRole assignRole(String userId) {
        try {
            return chefService.findById(userId).getRole();
        } catch (RuntimeException ignored) {}
        try {
            return participantService.findById(userId).getRole();
        } catch (RuntimeException ignored) {}
        try {
            return kitcherObserverService.findById(userId).getRole();
        } catch (RuntimeException ignored) {}

        throw new RuntimeException("User not found");
    }
}
