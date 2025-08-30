package com.springbackend.cbs.service;

import com.springbackend.cbs.dto.AdminDTO;
import com.springbackend.cbs.dto.ChefDTO;
import com.springbackend.cbs.dto.MenuDTO;
import com.springbackend.cbs.models.Admin;
import com.springbackend.cbs.models.Chef;
import com.springbackend.cbs.models.Menu;
import com.springbackend.cbs.repositories.AdminRepository;
import com.springbackend.cbs.repositories.ChefRepository;
import com.springbackend.cbs.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
	@Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private MenuRepository menuRepository;

    
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdminDTO loginAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid admin credentials"));

        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("Invalid admin credentials");
        }

        return convertToDTO(admin);
    }


    public AdminDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id " + id));
        return convertToDTO(admin);
    }
    public ChefDTO addChef(ChefDTO chefDTO) {
        Chef chef = convertToChefEntity(chefDTO);
        Chef savedChef = chefRepository.save(chef);
        return convertToChefDTO(savedChef);
    }

    public List<ChefDTO> getAllChefs() {
        return chefRepository.findAll()
                .stream()
                .map(this::convertToChefDTO)
                .collect(Collectors.toList());
    }

    public Optional<ChefDTO> getChefById(Long id) {
        return chefRepository.findById(id)
                .map(this::convertToChefDTO);
    }

    public ChefDTO updateChef(Long id, ChefDTO updatedChefDTO) {
        Chef chef = chefRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef not found with id " + id));

        chef.setName(updatedChefDTO.getName());
        chef.setSpeciality(updatedChefDTO.getSpeciality());
        chef.setExperience(updatedChefDTO.getExperience());
        chef.setEmail(updatedChefDTO.getEmail());
        chef.setPassword(updatedChefDTO.getPassword());

        return convertToChefDTO(chefRepository.save(chef));
    }

    public void deleteChef(Long id) {
        chefRepository.deleteById(id);
    }

    // ==================== Menu Management ====================
    public MenuDTO addMenu(MenuDTO menuDTO) {
        Menu menu = convertToMenuEntity(menuDTO);
        Menu savedMenu = menuRepository.save(menu);
        return convertToMenuDTO(savedMenu);
    }

    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map(this::convertToMenuDTO)
                .collect(Collectors.toList());
    }

    public Optional<MenuDTO> getMenuById(Long id) {
        return menuRepository.findById(id)
                .map(this::convertToMenuDTO);
    }

    public MenuDTO updateMenu(Long id, MenuDTO updatedMenuDTO) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id " + id));

        menu.setName(updatedMenuDTO.getName());
        menu.setPrice(updatedMenuDTO.getPrice());
        menu.setDescription(updatedMenuDTO.getDescription());
        menu.setImageUrl(updatedMenuDTO.getImageUrl());

        if (updatedMenuDTO.getChefId() != null) {
            Chef chef = chefRepository.findById(updatedMenuDTO.getChefId())
                    .orElseThrow(() -> new RuntimeException("Chef not found with id " + updatedMenuDTO.getChefId()));
            menu.setChef(chef);
        }

        return convertToMenuDTO(menuRepository.save(menu));
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    // ==================== Converters ====================
    private Chef convertToChefEntity(ChefDTO dto) {
        Chef chef = new Chef();
        chef.setId(dto.getId());
        chef.setName(dto.getName());
        chef.setEmail(dto.getEmail());
        chef.setPassword(dto.getPassword());
        chef.setSpeciality(dto.getSpeciality());
        chef.setExperience(dto.getExperience());
        return chef;
    }

    private ChefDTO convertToChefDTO(Chef chef) {
        ChefDTO dto = new ChefDTO();
        dto.setId(chef.getId());
        dto.setName(chef.getName());
        dto.setEmail(chef.getEmail());
        dto.setPassword(chef.getPassword());
        dto.setSpeciality(chef.getSpeciality());
        dto.setExperience(chef.getExperience());
        return dto;
    }

    private Menu convertToMenuEntity(MenuDTO dto) {
        Menu menu = new Menu();
        menu.setId(dto.getId());
        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());

        if (dto.getChefId() != null) {
            Chef chef = chefRepository.findById(dto.getChefId())
                    .orElseThrow(() -> new RuntimeException("Chef not found with id " + dto.getChefId()));
            menu.setChef(chef);
        }

        return menu;
    }

    private MenuDTO convertToMenuDTO(Menu menu) {
        return new MenuDTO(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getImageUrl(),
                menu.getChef() != null ? menu.getChef().getId() : null
        );
    }
    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setChefId(admin.getChef() != null ? admin.getChef().getId() : null);
        dto.setMenuId(admin.getMenu() != null ? admin.getMenu().getId() : null);
        return dto;
    }
}
