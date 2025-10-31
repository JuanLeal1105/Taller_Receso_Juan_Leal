package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import com.example.TallerReceso.Model.document.KitchenObserver;
import com.example.TallerReceso.Service.KitcherObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observers")
public class KitchenObserverController {
    @Autowired
    private KitcherObserverService kitcherObserverService;

    @PostMapping
    public ResponseEntity<ResponseDTO<KitchenObserver>> addKitchenObserver(@RequestBody KitchenObserver kitchenObserver) {
        KitchenObserver saved = kitcherObserverService.save(kitchenObserver);
        return ResponseEntity.ok(ResponseDTO.success(saved, "Succedfully added Kitchen Observer"));
    }

    @GetMapping
    public ResponseEntity<List<KitchenObserver>> getAllKitchenObservers() {
        return ResponseEntity.ok(kitcherObserverService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDTO<KitchenObserver>> getKitchenObserverById(@PathVariable String id) {
        KitchenObserver saved = kitcherObserverService.findById(id);
        return ResponseEntity.ok(ResponseDTO.success(saved, "Kitchen Observer found"));
    }
}
