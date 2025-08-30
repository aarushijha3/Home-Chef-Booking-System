package com.springbackend.cbs.controllers;

import com.springbackend.cbs.dto.AdminDTO;
import com.springbackend.cbs.dto.ChefDTO;
import com.springbackend.cbs.dto.MenuDTO;
import com.springbackend.cbs.models.Chef;
import com.springbackend.cbs.models.Menu;
import com.springbackend.cbs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @PostMapping("/login")
    public ResponseEntity<AdminDTO> loginAdmin(@RequestBody AdminDTO loginData) {
        AdminDTO admin = adminService.loginAdmin(loginData.getEmail(), loginData.getPassword());
        return ResponseEntity.ok(admin);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }
    @PostMapping("/chefs")
    public ChefDTO addChef(@RequestBody ChefDTO chef) {
        return adminService.addChef(chef);
    }

    @GetMapping("/chefs")
    public List<ChefDTO> getAllChefs() {
        return adminService.getAllChefs();
    }

    @GetMapping("/chefs/{id}")
    public ChefDTO getChefById(@PathVariable Long id) {
        return adminService.getChefById(id)
                .orElseThrow(() -> new RuntimeException("Chef not found with id " + id));
    }

    @PutMapping("/chefs/{id}")
    public ChefDTO updateChef(@PathVariable Long id, @RequestBody ChefDTO chef) {
        return adminService.updateChef(id, chef);
    }

    @DeleteMapping("/chefs/{id}")
    public String deleteChef(@PathVariable Long id) {
        adminService.deleteChef(id);
        return "Chef deleted successfully";
    }

    // ==================== Menu Endpoints ====================
    @PostMapping("/menus")
    public MenuDTO addMenu(@RequestBody MenuDTO menu) {
        return adminService.addMenu(menu);
    }

    @GetMapping("/menus")
    public List<MenuDTO> getAllMenus() {
        return adminService.getAllMenus();
    }

    @GetMapping("/menus/{id}")
    public MenuDTO getMenuById(@PathVariable Long id) {
        return adminService.getMenuById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id " + id));
    }

    @PutMapping("/menus/{id}")
    public MenuDTO updateMenu(@PathVariable Long id, @RequestBody MenuDTO menu) {
        return adminService.updateMenu(id, menu);
    }

    @DeleteMapping("/menus/{id}")
    public String deleteMenu(@PathVariable Long id) {
        adminService.deleteMenu(id);
        return "Menu deleted successfully";
    }
}
