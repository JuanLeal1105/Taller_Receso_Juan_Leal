package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.KitchenObserver;
import com.example.TallerReceso.Repository.KitchenObserverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitcherObserverService {
    @Autowired
    private KitchenObserverRepository kitcherObserverRepository;


    public KitchenObserver save(KitchenObserver kitchenObserver) {
        KitchenObserver savedKitchenObserver = kitcherObserverRepository.save(kitchenObserver);
        return savedKitchenObserver;
    }

    public List<KitchenObserver> findAll() {
        return kitcherObserverRepository.findAll();
    }

    public KitchenObserver findById(String id) {
        return kitcherObserverRepository.findById(id).orElseThrow(() -> new RuntimeException("Kitchen Observer not found"));
    }
}
