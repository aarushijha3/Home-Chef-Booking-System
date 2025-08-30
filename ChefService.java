package com.springbackend.cbs.service;

import com.springbackend.cbs.dto.ChefDTO;
import com.springbackend.cbs.models.Chef;
import com.springbackend.cbs.repositories.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    // Convert Entity to DTO
    private ChefDTO convertToDTO(Chef chef) {
        ChefDTO dto = new ChefDTO();
        dto.setId(chef.getId());
        dto.setName(chef.getName());
        dto.setEmail(chef.getEmail());
        dto.setPassword(chef.getPassword());
        dto.setSpeciality(chef.getSpeciality());
        dto.setExperience(chef.getExperience());
        return dto;
    }

    // Convert DTO to Entity
    private Chef convertToEntity(ChefDTO dto) {
        Chef chef = new Chef();
        chef.setId(dto.getId());
        chef.setName(dto.getName());
        chef.setEmail(dto.getEmail());
        chef.setPassword(dto.getPassword());
        chef.setSpeciality(dto.getSpeciality());
        chef.setExperience(dto.getExperience());
        return chef;
    }

    public List<ChefDTO> getAllChef() {
        try {
            List<Chef> chefs = chefRepository.findAll();
            return chefs.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error in getAllChef: " + e.getMessage());
            return null;
        }
    }

    public ChefDTO getChefById(Long id) {
        try {
            Optional<Chef> chef = chefRepository.findById(id);
            return chef.map(this::convertToDTO).orElse(null);
        } catch (Exception e) {
            System.out.println("Error in getChefById: " + e.getMessage());
            return null;
        }
    }

    public ChefDTO createChef(ChefDTO dto) {
        try {
            Chef chef = convertToEntity(dto);
            Chef savedChef = chefRepository.save(chef);
            return convertToDTO(savedChef);
        } catch (Exception e) {
            System.out.println("Error in createChef: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteChef(Long id) {
        try {
            if (chefRepository.existsById(id)) {
                chefRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error in deleteChef: " + e.getMessage());
            return false;
        }
    }

    public ChefDTO updateChef(Long id, ChefDTO updatedDto) {
        try {
            Optional<Chef> optionalChef = chefRepository.findById(id);
            if (optionalChef.isPresent()) {
                Chef chef = optionalChef.get();
                chef.setName(updatedDto.getName());
                chef.setEmail(updatedDto.getEmail());
                chef.setPassword(updatedDto.getPassword());
                chef.setSpeciality(updatedDto.getSpeciality());
                chef.setExperience(updatedDto.getExperience());
                Chef updatedChef = chefRepository.save(chef);
                return convertToDTO(updatedChef);
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error in updateChef: " + e.getMessage());
            return null;
        }
    }
}
