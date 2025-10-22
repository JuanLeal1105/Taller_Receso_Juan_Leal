package com.example.TallerReceso.Repository;

import com.example.TallerReceso.Model.document.KitchenObserver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenObserverRepository extends MongoRepository<KitchenObserver, String> {
}
