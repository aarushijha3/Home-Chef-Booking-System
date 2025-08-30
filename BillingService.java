package com.springbackend.cbs.service;

import com.springbackend.cbs.dto.BillingDTO;
import com.springbackend.cbs.models.Billing;
import com.springbackend.cbs.models.Booking;
import com.springbackend.cbs.repositories.BillingRepository;
import com.springbackend.cbs.repositories.BookingRepository;
import com.springbackend.cbs.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;
    
    private BillingDTO convertToDTO(Billing billing) {
        BillingDTO dto = new BillingDTO();
        dto.setId(billing.getId());
        dto.setUserid(billing.getUserid());
        dto.setAmount(billing.getAmount());
        dto.setStatus(billing.getStatus());

        if (billing.getBooking() != null) {
            dto.setBookingId(billing.getBooking().getId());
        }

        return dto;
    }

    private Billing convertToEntity(BillingDTO dto) {
        Billing billing = new Billing();
        billing.setId(dto.getId());
        billing.setUserid(dto.getUserid());
        billing.setAmount(dto.getAmount());
        billing.setStatus(dto.getStatus());

        if (dto.getBookingId() != null) {
            Optional<Booking> booking = bookingRepository.findById(dto.getBookingId());
            booking.ifPresent(billing::setBooking);
        }

        return billing;
    }

    public List<BillingDTO> getAllBillings() {
        return billingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BillingDTO getBillingById(Long id) {
        try {
            Optional<Billing> billing = billingRepository.findById(id);
            return billing.map(this::convertToDTO).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public BillingDTO createBilling(BillingDTO dto) {
        try {
            Billing billing = convertToEntity(dto);
            Billing saved = billingRepository.save(billing);
            return convertToDTO(saved);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteBilling(Long id) {
        try {
            if (billingRepository.existsById(id)) {
                billingRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public BillingDTO updateBilling(Long id, BillingDTO dto) {
        try {
            return billingRepository.findById(id).map(existing -> {
                existing.setUserid(dto.getUserid());
                existing.setAmount(dto.getAmount());
                existing.setStatus(dto.getStatus());

                if (dto.getBookingId() != null) {
                    Optional<Booking> booking = bookingRepository.findById(dto.getBookingId());
                    booking.ifPresent(existing::setBooking);
                } else {
                    existing.setBooking(null); // Clear if null
                }

                Billing updated = billingRepository.save(existing);
                return convertToDTO(updated);
            }).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
