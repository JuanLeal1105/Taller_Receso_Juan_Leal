package com.example.TallerReceso.Model.recipeCreator;

import com.example.TallerReceso.Dto.recipeDTO.RecipeDTO;
import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.Service.ChefService;
import com.example.TallerReceso.Service.KitcherObserverService;
import com.example.TallerReceso.Service.ParticipantService;
import com.example.TallerReceso.util.KitchenRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class RecipeCreator {
    private ChefService chefService;
    private ParticipantService participantService;
    private KitcherObserverService kitcherObserverService;
    public RecipeCreator(ChefService chefService, ParticipantService participantService, KitcherObserverService kitcherObserverService) {
        this.chefService = chefService;
        this.participantService = participantService;
        this.kitcherObserverService = kitcherObserverService;
    }

    public Recipe createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setId(UUID.randomUUID().toString());
        recipe.setRecipeName(recipeDTO.getRecipeName());
        recipe.setRecipeDescription(recipeDTO.getRecipeDescription());
        recipe.setIngredients((ArrayList<Ingredient>) (recipeDTO.getDetails().get("ingredients")).stream().map(obj -> (Ingredient) obj).toList());
        recipe.setSteps((ArrayList<String>) (recipeDTO.getDetails().get("steps")).stream().map(obj -> (String) obj).toList());
        recipe.setUserId(recipeDTO.getUserId());
        recipe.setUserName(assignUserName(recipeDTO.getUserId()));
        recipe.setRole(assignRole(recipeDTO.getUserId()));
        return recipe;
    }

    private String assignUserName(String userId) {
        if(chefService.findById(userId) != null) {
            return chefService.findById(userId).getChefName();
        } else if (participantService.findById(userId) != null) {
            return participantService.findById(userId).getParticipantName();
        }else if (kitcherObserverService.findById(userId) != null) {
            return kitcherObserverService.findById(userId).getObserverName();
        }else{
            throw new RuntimeException("User not found");
        }
    }
    private KitchenRole assignRole(String userId) {
        if(chefService.findById(userId) != null) {
            return chefService.findById(userId).getRole();
        } else if (participantService.findById(userId) != null) {
            return participantService.findById(userId).getRole();
        }else if (kitcherObserverService.findById(userId) != null) {
            return kitcherObserverService.findById(userId).getRole();
        }else{
            throw new RuntimeException("User not found");
        }
    }
}
