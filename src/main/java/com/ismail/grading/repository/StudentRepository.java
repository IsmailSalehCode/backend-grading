package com.ismail.grading.repository;

import org.springframework.data.repository.CrudRepository;

import com.ismail.grading.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}