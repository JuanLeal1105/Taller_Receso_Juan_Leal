package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.Repository.RecipeRepository;
import com.example.TallerReceso.Service.RecipeService;
import com.example.TallerReceso.Service.IngredientService;
import com.example.TallerReceso.util.KitchenRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Recipe recipe = new Recipe();
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        Recipe result = recipeService.save(recipe);

        assertEquals(recipe, result);
        verify(recipeRepository).save(recipe);
    }

    @Test
    void testFindAll() {
        Recipe recipe = new Recipe();
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        List<Recipe> result = recipeService.findAll();

        assertEquals(1, result.size());
        verify(recipeRepository).findAll();
    }

    @Test
    void testFindById() {
        Recipe recipe = new Recipe();
        when(recipeRepository.findById("r1")).thenReturn(Optional.of(recipe));

        Recipe result = recipeService.findById("r1");

        assertEquals(recipe, result);
        verify(recipeRepository).findById("r1");
    }

    @Test
    void testFindByIdThrowsIfEmpty() {
        when(recipeRepository.findById("nope")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> recipeService.findById("nope"));
        verify(recipeRepository).findById("nope");
    }

    @Test
    void testDeleteById() {
        doNothing().when(recipeRepository).deleteById("d1");

        recipeService.deleteById("d1");

        verify(recipeRepository).deleteById("d1");
    }

    @Test
    void testFindByKitchenRole() {
        Recipe recipe = new Recipe();
        when(recipeRepository.findByKitchenRole(KitchenRole.CHEF)).thenReturn(List.of(recipe));

        List<Recipe> result = recipeService.findByKitchenRole("chef");

        assertEquals(1, result.size());
        verify(recipeRepository).findByKitchenRole(KitchenRole.CHEF);
    }

    @Test
    void testFindByKitchenRoleThrows() {
        assertThrows(RuntimeException.class, () -> recipeService.findByKitchenRole("invalidrole"));
    }

    @Test
    void testFindBySeason() {
        Recipe recipe = new Recipe();
        when(recipeRepository.findBySeason("summer")).thenReturn(List.of(recipe));

        List<Recipe> result = recipeService.findBySeason("summer");

        assertEquals(1, result.size());
        verify(recipeRepository).findBySeason("summer");
    }

    @Test
    void testFindByIngredient() {
        Recipe r1 = new Recipe();
        ArrayList<com.example.TallerReceso.Model.document.Ingredient> ingredientList = new ArrayList<>();
        com.example.TallerReceso.Model.document.Ingredient ingredient = new com.example.TallerReceso.Model.document.Ingredient();
        ingredient.setIngredientName("Egg");
        ingredientList.add(ingredient);
        r1.setIngredients(ingredientList);

        Recipe r2 = new Recipe();
        r2.setIngredients(new ArrayList<>()); // Aquí también conviene ser explícito, aunque esté vacío
        when(recipeRepository.findAll()).thenReturn(List.of(r1, r2));

        List<Recipe> result = recipeService.findByIngredient("egg");

        assertEquals(1, result.size());
        assertTrue(result.contains(r1));
        verify(recipeRepository).findAll();
    }
}
