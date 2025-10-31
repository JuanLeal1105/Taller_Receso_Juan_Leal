package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.KitchenObserver;
import com.example.TallerReceso.Repository.KitchenObserverRepository;
import com.example.TallerReceso.Service.KitcherObserverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KitchenObserverServiceTest {

    @Mock
    private KitchenObserverRepository kitcherObserverRepository;

    @InjectMocks
    private KitcherObserverService kitcherObserverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        KitchenObserver ko = new KitchenObserver();
        when(kitcherObserverRepository.save(ko)).thenReturn(ko);

        KitchenObserver result = kitcherObserverService.save(ko);

        assertEquals(ko, result);
        verify(kitcherObserverRepository).save(ko);
    }

    @Test
    void testFindAll() {
        KitchenObserver ko = new KitchenObserver();
        when(kitcherObserverRepository.findAll()).thenReturn(List.of(ko));

        List<KitchenObserver> list = kitcherObserverService.findAll();

        assertEquals(1, list.size());
        verify(kitcherObserverRepository).findAll();
    }

    @Test
    void testFindById() {
        KitchenObserver ko = new KitchenObserver();
        when(kitcherObserverRepository.findById("id1")).thenReturn(Optional.of(ko));

        KitchenObserver result = kitcherObserverService.findById("id1");

        assertEquals(ko, result);
        verify(kitcherObserverRepository).findById("id1");
    }

    @Test
    void testFindByIdNotFound() {
        when(kitcherObserverRepository.findById("none")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> kitcherObserverService.findById("none"));
        verify(kitcherObserverRepository).findById("none");
    }
}

