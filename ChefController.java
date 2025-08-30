package com.springbackend.cbs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springbackend.cbs.dto.ChefDTO;

import com.springbackend.cbs.service.ChefService;

@RestController
@RequestMapping("/api/chefs")
@CrossOrigin(origins = "http://localhost:5173")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping
    public ResponseEntity<List<ChefDTO>> getAllChefs() {
        List<ChefDTO> chefs = chefService.getAllChef();
        return ResponseEntity.ok(chefs);
    }

    @PostMapping
    public ResponseEntity<ChefDTO> createChef(@RequestBody ChefDTO chefdto) {
        ChefDTO createdChef = chefService.createChef(chefdto);
        return ResponseEntity.ok(createdChef);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChefDTO> getChefById(@PathVariable Long id) {
        ChefDTO chef = chefService.getChefById(id);
        if (chef != null) {
            return ResponseEntity.ok(chef);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChefDTO> updateChef(@PathVariable Long id, @RequestBody ChefDTO updatedChef) {
        ChefDTO chef = chefService.updateChef(id, updatedChef);
        if (chef != null) {
            return ResponseEntity.ok(chef);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChef(@PathVariable Long id) {
        boolean deleted = chefService.deleteChef(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
