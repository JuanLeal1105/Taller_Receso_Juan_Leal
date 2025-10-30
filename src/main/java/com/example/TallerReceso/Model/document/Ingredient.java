package com.example.TallerReceso.Model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Ingredients")
public class Ingredient {
    @Id
    private String id;

    private String ingredientName;
    private String ingredientDescription;
}
