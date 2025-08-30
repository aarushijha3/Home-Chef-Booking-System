package com.springbackend.cbs.repositories;

import com.springbackend.cbs.models.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<Chef, Long> {
}
