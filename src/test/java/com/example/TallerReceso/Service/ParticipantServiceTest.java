package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.Participant;
import com.example.TallerReceso.Repository.ParticipantRepository;
import com.example.TallerReceso.Service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Participant participant = new Participant();
        when(participantRepository.save(participant)).thenReturn(participant);

        Participant result = participantService.save(participant);

        assertEquals(participant, result);
        verify(participantRepository).save(participant);
    }

    @Test
    void testFindAll() {
        Participant participant = new Participant();
        when(participantRepository.findAll()).thenReturn(List.of(participant));

        List<Participant> result = participantService.findAll();

        assertEquals(1, result.size());
        verify(participantRepository).findAll();
    }

    @Test
    void testFindById() {
        Participant participant = new Participant();
        when(participantRepository.findById("id1")).thenReturn(Optional.of(participant));

        Participant result = participantService.findById("id1");

        assertEquals(participant, result);
        verify(participantRepository).findById("id1");
    }

    @Test
    void testFindByIdNotFound() {
        when(participantRepository.findById("notfound")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> participantService.findById("notfound"));
        verify(participantRepository).findById("notfound");
    }
}
