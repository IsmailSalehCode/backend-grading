package com.ismail.grading.service;

import java.util.List;
import java.util.Set;

import com.ismail.grading.entity.Course;
import com.ismail.grading.entity.Student;

public interface CourseService {
    Course getCourse(Long id);

    Course saveCourse(Course course);

    void deleteCourse(Long id);

    Course addStudentToCourse(Long studentId, Long courseId);

    List<Course> getCourses();

    Set<Student> getEnrolledStudents(Long id);
}