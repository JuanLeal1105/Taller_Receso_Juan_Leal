package com.example.TallerReceso.Service;

import com.example.TallerReceso.Dto.recipeDTO.RecipeDTO;
import com.example.TallerReceso.Dto.recipeDTO.RecipeResponseDTO;
import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.Repository.RecipeRepository;
import com.example.TallerReceso.util.KitchenRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService  {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientService ingredientService;

    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }
    public List<Recipe> findAll(){
        return recipeRepository.findAll();
    }
    public Recipe findById(String id){
        return recipeRepository.findById(id).orElseThrow(()-> new RuntimeException("The recipe does not exist"));
    }
    public void deleteById(String id){
        recipeRepository.deleteById(id);
    }

    public List<Recipe> findByKitchenRole (String kitchenRole){
        KitchenRole role;
        try{
            role = KitchenRole.valueOf(kitchenRole.toUpperCase());
        }catch (Exception e){
            throw new RuntimeException("Invalid kitchen role");
        }
        return recipeRepository.findByKitchenRole(role);
    }

    public List<Recipe> findBySeason(String season){
        return recipeRepository.findBySeason(season);
    }

    public List<Recipe> findByIngredient(String ingredient){
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .anyMatch(i -> i.getIngredientName().equalsIgnoreCase(ingredient)))
                .collect(Collectors.toList());
    }


}
