package com.ismail.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.ismail.gradesubmission.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}