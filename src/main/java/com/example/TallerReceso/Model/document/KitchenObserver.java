package com.example.TallerReceso.Model.document;

import com.example.TallerReceso.util.KitchenRole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document (collection = "kitchen_observers")
public class KitchenObserver {
    @Id
    private String id;

    private String observerName;
    private String observerEmail;
    private KitchenRole role = KitchenRole.OBSERVER;

}
