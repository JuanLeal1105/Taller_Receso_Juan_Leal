package com.example.TallerReceso.Repository;

import com.example.TallerReceso.Model.document.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, String> {
    Ingredient findIngredientByIngredientName (String name);
}
