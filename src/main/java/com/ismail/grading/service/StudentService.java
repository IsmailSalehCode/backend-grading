package com.ismail.grading.service;

import java.util.List;
import java.util.Set;

import com.ismail.grading.entity.Course;
import com.ismail.grading.entity.Student;

public interface StudentService {
    Student getStudent(Long id);

    Student saveStudent(Student student);

    void deleteStudent(Long id);

    List<Student> getStudents();

    Set<Course> getEnrolledCourses(Long id);
}