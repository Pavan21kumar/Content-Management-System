package com.example.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	boolean existsByEmail(String email);

	Optional<Users> findByEmail(String email);
}
