package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Controller.ParticipantController;
import com.example.TallerReceso.Model.document.Participant;
import com.example.TallerReceso.Service.ParticipantService;
import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ParticipantControllerTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testAddParticipant() {
        Participant participant = new Participant();
        when(participantService.save(participant)).thenReturn(participant);
        var response = participantController.addParticipant(participant);
        assertEquals("Succesfully added participant", response.getBody().getMessage());
        assertEquals(participant, response.getBody().getData());
        verify(participantService).save(participant);
    }

    @Test
    void testGetAllParticipants() {
        Participant participant = new Participant();
        when(participantService.findAll()).thenReturn(List.of(participant));
        var response = participantController.getAllParticipants();
        assertEquals(1, response.getBody().size());
        verify(participantService).findAll();
    }

    @Test
    void testGetParticipantById() {
        Participant participant = new Participant();
        when(participantService.findById("p1")).thenReturn(participant);
        var response = participantController.getParticipantById("p1");
        assertEquals("Succesfully found participant", response.getBody().getMessage());
        assertEquals(participant, response.getBody().getData());
        verify(participantService).findById("p1");
    }

    @Test
    void testGetParticipantById_NotFound() {
        when(participantService.findById("nope")).thenThrow(new RuntimeException("Participant not found"));
        assertThrows(RuntimeException.class, () -> participantController.getParticipantById("nope"));
        verify(participantService).findById("nope");
    }

    @Test
    void testAddParticipant_Null() {
        when(participantService.save(null)).thenReturn(null);
        var response = participantController.addParticipant(null);
        assertNull(response.getBody().getData());
        verify(participantService).save(null);
    }
}

