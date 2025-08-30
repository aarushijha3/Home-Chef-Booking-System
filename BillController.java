package com.springbackend.cbs.controllers;

import com.springbackend.cbs.dto.BillingDTO;
import com.springbackend.cbs.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billings")
@CrossOrigin(origins = "http://localhost:5173")
public class BillController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public List<BillingDTO> getAllBillings() {
        return billingService.getAllBillings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingDTO> getBillingById(@PathVariable Long id) {
        BillingDTO dto = billingService.getBillingById(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BillingDTO> createBilling(@RequestBody BillingDTO billingDTO) {
        BillingDTO created = billingService.createBilling(billingDTO);
        return (created != null) ? ResponseEntity.ok(created) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillingDTO> updateBilling(@PathVariable Long id, @RequestBody BillingDTO billingDTO) {
        BillingDTO updated = billingService.updateBilling(id, billingDTO);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Long id) {
        boolean deleted = billingService.deleteBilling(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
