package com.ismail.gradesubmission.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ismail.gradesubmission.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
	// boolean existsByUsername(String username);
}