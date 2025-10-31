package com.example.TallerReceso.Controller;

import com.example.TallerReceso.Dto.recipeDTO.ResponseDTO;
import com.example.TallerReceso.Model.document.Chef;
import com.example.TallerReceso.Service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")
public class ChefController {
    @Autowired
    private ChefService chefService;

    @PostMapping
    public ResponseEntity<ResponseDTO<Chef>> addChef(@RequestBody Chef chef) {
        Chef savedChef = chefService.save(chef);
        return ResponseEntity.ok(ResponseDTO.success(savedChef,"Succesfully added chef"));
    }

    @GetMapping
    public ResponseEntity<List<Chef>> getAllChefs() {
        return ResponseEntity.ok(chefService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDTO<Chef>> getChefById(@PathVariable String id) {
        Chef chef = chefService.findById(id);
        return ResponseEntity.ok(ResponseDTO.success(chef,"Chef found"));
    }
}
