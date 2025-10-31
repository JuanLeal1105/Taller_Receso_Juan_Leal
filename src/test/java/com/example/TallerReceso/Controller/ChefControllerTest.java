package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Controller.ChefController;
import com.example.TallerReceso.Model.document.Chef;
import com.example.TallerReceso.Service.ChefService;
import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ChefControllerTest {

    @Mock
    private ChefService chefService;
    @InjectMocks
    private ChefController chefController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testAddChef() {
        Chef chef = new Chef();
        when(chefService.save(chef)).thenReturn(chef);
        var response = chefController.addChef(chef);
        assertEquals("Succesfully added chef", response.getBody().getMessage());
        assertEquals(chef, response.getBody().getData());
        verify(chefService).save(chef);
    }

    @Test
    void testGetAllChefs() {
        Chef chef = new Chef();
        when(chefService.findAll()).thenReturn(List.of(chef));
        var response = chefController.getAllChefs();
        assertEquals(1, response.getBody().size());
        verify(chefService).findAll();
    }

    @Test
    void testGetChefById() {
        Chef chef = new Chef();
        when(chefService.findById("c1")).thenReturn(chef);
        var response = chefController.getChefById("c1");
        assertEquals("Chef found", response.getBody().getMessage());
        assertEquals(chef, response.getBody().getData());
        verify(chefService).findById("c1");
    }

    @Test
    void testGetChefById_NotFound() {
        when(chefService.findById("nope")).thenThrow(new RuntimeException("Chef not found"));
        assertThrows(RuntimeException.class, () -> chefController.getChefById("nope"));
        verify(chefService).findById("nope");
    }

    @Test
    void testAddChef_NullBody() {
        when(chefService.save(null)).thenReturn(null);
        var response = chefController.addChef(null);
        assertNull(response.getBody().getData());
        verify(chefService).save(null);
    }
}


