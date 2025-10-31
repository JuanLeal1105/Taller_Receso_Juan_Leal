package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Controller.KitchenObserverController;
import com.example.TallerReceso.Model.document.KitchenObserver;
import com.example.TallerReceso.Service.KitcherObserverService;
import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class KitchenObserverControllerTest {

    @Mock
    private KitcherObserverService kitcherObserverService;
    @InjectMocks
    private KitchenObserverController kitchenObserverController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testAddKitchenObserver() {
        KitchenObserver ko = new KitchenObserver();
        when(kitcherObserverService.save(ko)).thenReturn(ko);
        var response = kitchenObserverController.addKitchenObserver(ko);
        assertEquals("Succedfully added Kitchen Observer", response.getBody().getMessage());
        assertEquals(ko, response.getBody().getData());
        verify(kitcherObserverService).save(ko);
    }

    @Test
    void testGetAllKitchenObservers() {
        KitchenObserver observer = new KitchenObserver();
        when(kitcherObserverService.findAll()).thenReturn(List.of(observer));
        var response = kitchenObserverController.getAllKitchenObservers();
        assertEquals(1, response.getBody().size());
        verify(kitcherObserverService).findAll();
    }

    @Test
    void testGetKitchenObserverById() {
        KitchenObserver observer = new KitchenObserver();
        when(kitcherObserverService.findById("ob1")).thenReturn(observer);
        var response = kitchenObserverController.getKitchenObserverById("ob1");
        assertEquals("Kitchen Observer found", response.getBody().getMessage());
        assertEquals(observer, response.getBody().getData());
        verify(kitcherObserverService).findById("ob1");
    }

    @Test
    void testGetKitchenObserverById_NotFound() {
        when(kitcherObserverService.findById("nope")).thenThrow(new RuntimeException("Kitchen Observer not found"));
        assertThrows(RuntimeException.class, () -> kitchenObserverController.getKitchenObserverById("nope"));
        verify(kitcherObserverService).findById("nope");
    }

    @Test
    void testAddKitchenObserver_Null() {
        when(kitcherObserverService.save(null)).thenReturn(null);
        var response = kitchenObserverController.addKitchenObserver(null);
        assertNull(response.getBody().getData());
        verify(kitcherObserverService).save(null);
    }
}


