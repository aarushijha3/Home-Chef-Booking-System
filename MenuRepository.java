package com.springbackend.cbs.repositories;

import com.springbackend.cbs.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
