package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Repository.IngredientRepository;
import com.example.TallerReceso.Service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Ingredient ingredient = new Ingredient();
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);

        Ingredient result = ingredientService.save(ingredient);

        assertEquals(ingredient, result);
        verify(ingredientRepository).save(ingredient);
    }

    @Test
    void testFindByName() {
        Ingredient ingredient = new Ingredient();
        when(ingredientRepository.findIngredientByIngredientName("salt")).thenReturn(ingredient);

        Ingredient result = ingredientService.findByName("salt");

        assertEquals(ingredient, result);
        verify(ingredientRepository).findIngredientByIngredientName("salt");
    }
}

