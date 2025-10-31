package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.Chef;
import com.example.TallerReceso.Repository.ChefRepository;
import com.example.TallerReceso.Service.ChefService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChefServiceTest {

    @Mock
    private ChefRepository chefRepository;

    @InjectMocks
    private ChefService chefService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Chef chef = new Chef();
        when(chefRepository.save(chef)).thenReturn(chef);

        Chef result = chefService.save(chef);

        assertEquals(chef, result);
        verify(chefRepository).save(chef);
    }

    @Test
    void testFindAll() {
        Chef chef = new Chef();
        when(chefRepository.findAll()).thenReturn(List.of(chef));

        List<Chef> result = chefService.findAll();

        assertEquals(1, result.size());
        verify(chefRepository).findAll();
    }

    @Test
    void testFindById() {
        Chef chef = new Chef();
        when(chefRepository.findById("id1")).thenReturn(Optional.of(chef));

        Chef result = chefService.findById("id1");

        assertEquals(chef, result);
        verify(chefRepository).findById("id1");
    }

    @Test
    void testFindByIdNotFound() {
        when(chefRepository.findById("notfound")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> chefService.findById("notfound"));
        verify(chefRepository).findById("notfound");
    }
}

