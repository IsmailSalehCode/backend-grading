package com.ltp.gradesubmission;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class GradeSubmissionApplication implements CommandLineRunner {

	StudentRepository studentRepository;
	CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(GradeSubmissionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student[] students = new Student[] {
				new Student("Ismail Saleh", LocalDate.parse(("1999-10-11"))),
				new Student("Ivan Stoikov", LocalDate.parse(("2000-03-01"))),
				new Student("Maria Benkovska", LocalDate.parse(("2000-09-19"))),
				new Student("Petra Elenova", LocalDate.parse(("2000-07-30")))
		};

		for (int i = 0; i < students.length; i++) {
			studentRepository.save(students[i]);
		}

		Course[] courses = new Course[] {
				new Course("Java Programming Foundations", "CS101",
						"Introduction to Java programming, covering syntax, basic algorithms, and object-oriented programming principles."),
				new Course("Data Structures in C++", "CS201",
						"In-depth study of data structures such as linked lists, trees, and graphs, and algorithms for sorting and searching using C++."),
				new Course("Relational Database Design and SQL", "CS310",
						"Design and implementation of relational databases, emphasizing SQL for querying and managing data."),
				new Course("Full Stack Web Development with Vue and Node.js", "CS402",
						"Building dynamic and responsive web applications using Vue for the frontend and Node.js for the backend."),
				new Course("Operating Systems Concepts and Practice", "CS451",
						"Understanding and implementation of operating system concepts, including processes, memory management, and file systems."),
				new Course("Machine Learning for Image Recognition", "CS501",
						"Exploration of machine learning algorithms, with a focus on image recognition applications using Python and TensorFlow."),
				new Course("Agile Software Development and Scrum", "CS601",
						"Practical application of agile methodologies, emphasizing Scrum practices in software development projects.")
		};

		for (int i = 0; i < courses.length; i++) {
			courseRepository.save(courses[i]);
		}

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
