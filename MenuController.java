package com.springbackend.cbs.controllers;

import com.springbackend.cbs.dto.MenuDTO;

import com.springbackend.cbs.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "http://localhost:5173")
public class MenuController {

   

    @Autowired
    private MenuService menuService;

    // GET all menus
    @GetMapping
    public List<MenuDTO> getAllMenus() {
        return menuService.getAllMenus();
    }

    // GET menu by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long id) {
        MenuDTO menu = menuService.getMenuById(id);
        return (menu != null) ? ResponseEntity.ok(menu) : ResponseEntity.notFound().build();
    }

    // POST create menu (menu data only)
    @PostMapping
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menu) {
        try {
            MenuDTO createdMenu = menuService.createMenu(menu);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Or return ResponseEntity with a custom error object/message
        }
    }


    // PUT update menu
    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long id, @RequestBody MenuDTO updatedMenu) {
        MenuDTO updated = menuService.updateMenu(id, updatedMenu);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE menu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        boolean deleted = menuService.deleteMenu(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    
    
}
