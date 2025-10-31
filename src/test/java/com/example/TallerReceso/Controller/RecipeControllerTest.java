package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Controller.RecipeController;
import com.example.TallerReceso.Dto.recipeDTO.RecipeDTO;
import com.example.TallerReceso.Dto.recipeDTO.RecipeResponseDTO;
import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.Model.recipeCreator.RecipeCreator;
import com.example.TallerReceso.Service.RecipeService;
import com.example.TallerReceso.util.KitchenRole;
import com.example.TallerReceso.util.RecipeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;
    @Mock
    private RecipeCreator recipeCreator;
    @Mock
    private RecipeMapper recipeMapper;
    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testAddRecipe() {
        RecipeDTO dto = new RecipeDTO();
        Recipe recipe = new Recipe();
        Recipe saved = new Recipe();
        when(recipeCreator.createRecipe(dto)).thenReturn(recipe);
        when(recipeService.save(recipe)).thenReturn(saved);
        var response = recipeController.addRecipe(dto);
        assertEquals("Successfully added recipe", response.getBody().getMessage());
        assertEquals(saved, response.getBody().getData());
    }

    @Test
    void testAddRecipe_NullBody() {
        when(recipeCreator.createRecipe(null)).thenReturn(null);
        when(recipeService.save(null)).thenReturn(null);
        var response = recipeController.addRecipe(null);
        assertNull(response.getBody().getData());
    }

    @Test
    void testGetAllRecipes() {
        Recipe recipe = new Recipe();
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();
        when(recipeService.findAll()).thenReturn(List.of(recipe));
        when(recipeMapper.mapToRecipeResponseDTO(recipe)).thenReturn(responseDTO);
        var response = recipeController.getAllRecipes();
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testFindRecipeById() {
        Recipe recipe = new Recipe();
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();
        when(recipeService.findById("id1")).thenReturn(recipe);
        when(recipeMapper.mapToRecipeResponseDTO(recipe)).thenReturn(responseDTO);
        var response = recipeController.findRecipeById("id1");
        assertEquals("Successfully found recipe", response.getBody().getMessage());
        assertEquals(responseDTO, response.getBody().getData());
    }

    @Test
    void testFindRecipeById_NotFound() {
        when(recipeService.findById("not_found")).thenThrow(new RuntimeException("The recipe does not exist"));
        assertThrows(RuntimeException.class, () -> recipeController.findRecipeById("not_found"));
    }

    @Test
    void testFindByKitchenRole() {
        Recipe recipe = new Recipe();
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();
        when(recipeService.findByKitchenRole("CHEF")).thenReturn(List.of(recipe));
        when(recipeMapper.mapToRecipeResponseDTO(recipe)).thenReturn(responseDTO);
        var response = recipeController.findByKitchenRole("CHEF");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testFindByKitchenRole_Invalid() {
        when(recipeService.findByKitchenRole("NO_ROLE")).thenThrow(new RuntimeException("Invalid kitchen role"));
        assertThrows(RuntimeException.class, () -> recipeController.findByKitchenRole("NO_ROLE"));
    }

    @Test
    void testFindBySeason() {
        Recipe recipe = new Recipe();
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();
        when(recipeService.findBySeason("summer")).thenReturn(List.of(recipe));
        when(recipeMapper.mapToRecipeResponseDTO(recipe)).thenReturn(responseDTO);
        var response = recipeController.findBySeason("summer");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testFindByIngredient() {
        Recipe recipe = new Recipe();
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();
        when(recipeService.findByIngredient("Egg")).thenReturn(List.of(recipe));
        when(recipeMapper.mapToRecipeResponseDTO(recipe)).thenReturn(responseDTO);
        var response = recipeController.findByIngredient("Egg");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testDeleteRecipe() {
        doNothing().when(recipeService).deleteById("r1");
        var response = recipeController.deleteRecipe("r1");
        assertEquals("Recipe deleted", response.getBody().getMessage());
        verify(recipeService).deleteById("r1");
    }

    @Test
    void testUpdateRecipe() {
        RecipeDTO dto = new RecipeDTO();
        Recipe existing = new Recipe();
        Recipe updated = new Recipe();
        Recipe saved = new Recipe();
        RecipeResponseDTO respDTO = new RecipeResponseDTO();
        when(recipeService.findById("testid")).thenReturn(existing);
        when(recipeCreator.createRecipe(dto)).thenReturn(updated);
        when(recipeService.save(updated)).thenReturn(saved);
        when(recipeMapper.mapToRecipeResponseDTO(saved)).thenReturn(respDTO);
        var response = recipeController.updateRecipe("testid", dto);
        assertEquals("Recipe updated successfully", response.getBody().getMessage());
    }

    @Test
    void testUpdateRecipe_NotFound() {
        RecipeDTO dto = new RecipeDTO();
        when(recipeService.findById("missing")).thenThrow(new RuntimeException("The recipe does not exist"));
        assertThrows(RuntimeException.class, () -> recipeController.updateRecipe("missing", dto));
    }

    @Test
    void testPatchRecipeField() {
        Recipe recipe = new Recipe();
        Recipe saved = new Recipe();
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();

        Map<String, Object> updates = new HashMap<>();
        updates.put("recipeName", "New Name");

        when(recipeService.findById("idPatch")).thenReturn(recipe);
        when(recipeService.save(recipe)).thenReturn(saved);
        when(recipeMapper.mapToRecipeResponseDTO(saved)).thenReturn(responseDTO);

        var response = recipeController.patchRecipeField("idPatch", updates);

        assertEquals("Recipe patched successfully", response.getBody().getMessage());
        assertEquals(responseDTO, response.getBody().getData());
    }

    @Test
    void testPatchRecipeField_KitchenRoleError() {
        Recipe recipe = new Recipe();
        Map<String, Object> updates = new HashMap<>();
        updates.put("kitchenRole", "INVALID");
        when(recipeService.findById("idkrole")).thenReturn(recipe);
        // KitchenRole.valueOf lanzará IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> recipeController.patchRecipeField("idkrole", updates));
    }
}

