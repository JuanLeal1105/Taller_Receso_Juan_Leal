package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient findByName(String name){
        return ingredientRepository.findIngredientByIngredientName(name);
    }
}
