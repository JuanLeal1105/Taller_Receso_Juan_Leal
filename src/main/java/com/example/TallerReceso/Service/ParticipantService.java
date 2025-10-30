package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.Participant;
import com.example.TallerReceso.Repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public Participant save(Participant participant) {
        Participant savedParticipant = participantRepository.save(participant);
        return savedParticipant;
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public Participant findById(String id) {
        return participantRepository.findById(id).orElseThrow(() -> new RuntimeException("Participant not found"));
    }
}
