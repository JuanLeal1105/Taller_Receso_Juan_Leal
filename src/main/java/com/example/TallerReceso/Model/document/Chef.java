package com.example.TallerReceso.Model.document;

import com.example.TallerReceso.util.KitchenRole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Chefs")
public class Chef {
    @Id
    private String id;

    private String chefName;
    private String email;
    private KitchenRole role = KitchenRole.CHEF;
}
