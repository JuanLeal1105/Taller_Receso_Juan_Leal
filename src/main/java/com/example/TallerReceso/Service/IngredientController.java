package com.example.TallerReceso.Service;

import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import com.example.TallerReceso.Model.document.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<ResponseDTO<Ingredient>> addIngredient(@RequestBody Ingredient ingredient) {
        Ingredient saved = ingredientService.save(ingredient);
        return ResponseEntity.ok(ResponseDTO.success(saved, "Successfully added ingredient"));
    }
}
