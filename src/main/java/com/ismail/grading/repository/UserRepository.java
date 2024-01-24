package com.ismail.grading.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ismail.grading.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
}