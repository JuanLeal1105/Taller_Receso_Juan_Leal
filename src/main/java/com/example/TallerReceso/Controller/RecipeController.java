package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Dto.recipeDTO.RecipeDTO;
import com.example.TallerReceso.Dto.recipeDTO.RecipeResponseDTO;
import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.Model.recipeCreator.RecipeCreator;
import com.example.TallerReceso.Service.RecipeService;
import com.example.TallerReceso.util.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeCreator recipeCreator;
    @Autowired
    private RecipeMapper recipeMapper;

    @PostMapping
    public ResponseEntity<ResponseDTO<Recipe>> addRecipe(@RequestBody RecipeDTO dto) {
        Recipe recipe = recipeCreator.createRecipe(dto);
        Recipe saved = recipeService.save(recipe);
        return ResponseEntity.ok(ResponseDTO.success(saved, "Successfully added recipe"));
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponseDTO>> getAllRecipes() {
        List<Recipe> list = recipeService.findAll();
        List<RecipeResponseDTO> recipes = list.stream().map(recipeMapper::mapToRecipeResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<RecipeResponseDTO>> findRecipeById(@PathVariable String id) {
        Recipe recipe = recipeService.findById(id);
        RecipeResponseDTO recipeResponseDTO = recipeMapper.mapToRecipeResponseDTO(recipe);
        return ResponseEntity.ok(ResponseDTO.success(recipeResponseDTO, "Successfully found recipe"));
    }

    @GetMapping("/kitchenRole/{kitcheRole}")
    public ResponseEntity<List<RecipeResponseDTO>> findByKitchenRole(@PathVariable String kitcheRole) {
        List<Recipe> list = recipeService.findByKitchenRole(kitcheRole);
        List<RecipeResponseDTO> recipes = list.stream().map(recipeMapper::mapToRecipeResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/season/{season}")
    public ResponseEntity<List<RecipeResponseDTO>> findBySeason(@PathVariable String season) {
        List<Recipe> list = recipeService.findBySeason(season);
        List<RecipeResponseDTO> recipes = list.stream().map(recipeMapper::mapToRecipeResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/ingredient/{ingredient}")
    public ResponseEntity<List<RecipeResponseDTO>> findByIngredient(@PathVariable String ingredient) {
        List<Recipe> list = recipeService.findByIngredient(ingredient);
        List<RecipeResponseDTO> recipes = list.stream().map(recipeMapper::mapToRecipeResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(id);
        return ResponseEntity.ok(ResponseDTO.success(null, "Recipe deleted"));
    }

}
