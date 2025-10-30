package com.example.TallerReceso.Service;

import com.example.TallerReceso.Model.document.Chef;
import com.example.TallerReceso.Repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefService {
    @Autowired
    private  ChefRepository chefRepository;


    public Chef save(Chef chef) {
        Chef savedChef = chefRepository.save(chef);
        return savedChef;
    }

    public List<Chef> findAll() {
        return chefRepository.findAll();
    }

    public Chef findById(String id) {
        return chefRepository.findById(id).orElseThrow(() -> new RuntimeException("Chef not found"));
    }
}
