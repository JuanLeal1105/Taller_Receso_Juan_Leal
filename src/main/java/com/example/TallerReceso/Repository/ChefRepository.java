package com.example.TallerReceso.Repository;

import com.example.TallerReceso.Model.document.Chef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends MongoRepository<Chef,String> {
}
