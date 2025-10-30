package com.example.TallerReceso.Repository;

import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.util.KitchenRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByKitchenRole(KitchenRole role);
    List<Recipe> findBySeason(String season);
}
