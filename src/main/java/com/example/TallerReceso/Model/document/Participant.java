package com.example.TallerReceso.Model.document;

import com.example.TallerReceso.util.KitchenRole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document (collection = "Participants")
public class Participant {
    @Id
    private String id;

    private String participantName;
    private String participantEmail;
    private KitchenRole role = KitchenRole.PARTICIPANT;
}
