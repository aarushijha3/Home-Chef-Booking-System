package com.springbackend.cbs.service;

import com.springbackend.cbs.dto.BookingDTO;
import com.springbackend.cbs.models.Booking;
import com.springbackend.cbs.models.Chef;
import com.springbackend.cbs.models.Menu;
import com.springbackend.cbs.models.User;
import com.springbackend.cbs.repositories.BookingRepository;
import com.springbackend.cbs.repositories.ChefRepository;
import com.springbackend.cbs.repositories.MenuRepository;
import com.springbackend.cbs.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private MenuRepository menuRepository;

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public BookingDTO createBooking(BookingDTO dto) {
        try {
            Booking booking = convertToEntity(dto);
            Booking saved = bookingRepository.save(booking);
            return convertToDTO(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BookingDTO updateBooking(Long id, BookingDTO dto) {
        return bookingRepository.findById(id).map(existing -> {
            try {
                existing.setDate(dto.getDate());
               

                if (dto.getUserId() != null) {
                    userRepository.findById(dto.getUserId()).ifPresent(existing::setUser);
                }
                if (dto.getChefId() != null) {
                    chefRepository.findById(dto.getChefId()).ifPresent(existing::setChef);
                }
                if (dto.getMenuId() != null) {
                    menuRepository.findById(dto.getMenuId()).ifPresent(existing::setMenu);
                }

                return convertToDTO(bookingRepository.save(existing));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).orElse(null);
    }
    public List<BookingDTO> getBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Conversion Methods
    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setDate(booking.getDate());
       

        if (booking.getUser() != null) {
            dto.setUserId(booking.getUser().getId());
        }
        if (booking.getChef() != null) {
            dto.setChefId(booking.getChef().getId());
        }
        if (booking.getMenu() != null) {
            dto.setMenuId(booking.getMenu().getId());
        }
        return dto;
    }

    private Booking convertToEntity(BookingDTO dto) {
    	if (dto.getUserId() == null || dto.getChefId() == null || dto.getMenuId() == null) {
            throw new IllegalArgumentException("User ID, Chef ID, and Menu ID must not be null.");
        }
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setDate(dto.getDate());
      
     // Load and set User
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + dto.getUserId());
        }
        booking.setUser(userOpt.get());

        // Load and set Chef
        Optional<Chef> chefOpt = chefRepository.findById(dto.getChefId());
        if (chefOpt.isEmpty()) {
            throw new RuntimeException("Chef not found with ID: " + dto.getChefId());
        }
        booking.setChef(chefOpt.get());

        // Load and set Menu
        Optional<Menu> menuOpt = menuRepository.findById(dto.getMenuId());
        if (menuOpt.isEmpty()) {
            throw new RuntimeException("Menu not found with ID: " + dto.getMenuId());
        }
        booking.setMenu(menuOpt.get());

        

        return booking;
    }
}
