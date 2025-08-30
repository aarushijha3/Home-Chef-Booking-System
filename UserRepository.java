package com.springbackend.cbs.repositories;

import com.springbackend.cbs.models.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmailAndPassword(String email, String password);

}
