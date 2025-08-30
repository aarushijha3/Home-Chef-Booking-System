package com.springbackend.cbs.repositories;

import com.springbackend.cbs.models.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
