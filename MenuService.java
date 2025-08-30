package com.springbackend.cbs.service;

import com.springbackend.cbs.dto.MenuDTO;
import com.springbackend.cbs.models.Chef;
import com.springbackend.cbs.models.Menu;
import com.springbackend.cbs.repositories.ChefRepository;
import com.springbackend.cbs.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ChefRepository chefRepository;

    public List<MenuDTO> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuDTO> menuDTOs = new ArrayList<>();

        for (Menu menu : menus) {
            menuDTOs.add(convertToDTO(menu));
        }

        return menuDTOs;
    }

    public MenuDTO getMenuById(Long id) {
        Optional<Menu> menuOpt = menuRepository.findById(id);
        return menuOpt.map(this::convertToDTO).orElse(null);
    }

    public MenuDTO createMenu(MenuDTO menuDTO) {
        Menu menu = convertToEntity(menuDTO);
        Menu savedMenu = menuRepository.save(menu);
        return convertToDTO(savedMenu);
    }

    public MenuDTO updateMenu(Long id, MenuDTO updatedDTO) {
        return menuRepository.findById(id).map(menu -> {
            menu.setName(updatedDTO.getName());
            menu.setDescription(updatedDTO.getDescription());
            menu.setPrice(updatedDTO.getPrice());
            menu.setImageUrl(updatedDTO.getImageUrl());
            
            if (updatedDTO.getChefId() != null) {
                Optional<Chef> chefOpt = chefRepository.findById(updatedDTO.getChefId());
                chefOpt.ifPresent(menu::setChef);
            }
            return convertToDTO(menuRepository.save(menu));
        }).orElse(null);
    }

    public boolean deleteMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper methods
    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setImageUrl(menu.getImageUrl());
        
        if (menu.getChef() != null) {
            dto.setChefId(menu.getChef().getId()); 
        }
        return dto;
    }

    private Menu convertToEntity(MenuDTO dto) {
        Menu menu = new Menu();
        menu.setId(dto.getId());
        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        if (dto.getChefId() != null) {
            Optional<Chef> chefOpt = chefRepository.findById(dto.getChefId());
            chefOpt.ifPresent(menu::setChef);
        }
        return menu;
    }
}
