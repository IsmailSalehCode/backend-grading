package com.ismail.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.ismail.gradesubmission.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}