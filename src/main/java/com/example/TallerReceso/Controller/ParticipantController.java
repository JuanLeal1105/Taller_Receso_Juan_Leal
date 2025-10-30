package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import com.example.TallerReceso.Model.document.Participant;
import com.example.TallerReceso.Service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public ResponseEntity<ResponseDTO<Participant>> addParticipant(@RequestBody Participant participant) {
        Participant saved  = participantService.save(participant);
        return ResponseEntity.ok(ResponseDTO.success(saved, "Succesfully added participant"));
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        return ResponseEntity.ok(participantService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDTO<Participant>> getParticipantById(@PathVariable String id) {
        Participant participant = participantService.findById(id);
        return ResponseEntity.ok(ResponseDTO.success(participant, "Succesfully found participant"));
    }

}
