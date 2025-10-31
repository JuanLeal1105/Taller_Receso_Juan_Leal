package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Controller.IngredientController;
import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Service.IngredientService;
import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class IngredientControllerTest {

    @Mock
    private IngredientService ingredientService;
    @InjectMocks
    private IngredientController ingredientController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testAddIngredient() {
        Ingredient ingredient = new Ingredient();
        when(ingredientService.save(ingredient)).thenReturn(ingredient);
        var response = ingredientController.addIngredient(ingredient);
        assertEquals("Successfully added ingredient", response.getBody().getMessage());
        assertEquals(ingredient, response.getBody().getData());
        verify(ingredientService).save(ingredient);
    }

    @Test
    void testAddIngredient_Null() {
        when(ingredientService.save(null)).thenReturn(null);
        var response = ingredientController.addIngredient(null);
        assertNull(response.getBody().getData());
        verify(ingredientService).save(null);
    }
}

