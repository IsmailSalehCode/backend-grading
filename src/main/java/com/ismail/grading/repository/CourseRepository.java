package com.ismail.grading.repository;

import org.springframework.data.repository.CrudRepository;

import com.ismail.grading.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}