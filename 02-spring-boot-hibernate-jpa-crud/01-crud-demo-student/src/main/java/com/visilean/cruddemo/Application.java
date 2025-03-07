package com.visilean.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.visilean.cruddemo.dao.StudentDAO;
import com.visilean.cruddemo.entity.Student;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			 createStudent(studentDAO);
			createMultipleStudent(studentDAO);
//			readStudent(studentDAO);
//			queryForStudent(studentDAO);
//			findingByLastName(studentDAO);
//			updateTheStudent(studentDAO);
//			deleteTheStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		// create a student object

		System.out.println("Creating new student");
		Student stuTemp = new Student("Adnan", "Khan", "adnankhan@gmail.com");

		// save the student object

		System.out.println("Saving the student");
		studentDAO.save(stuTemp);

		// display the id of saved student

		System.out.println("The id of the student which just got saved is : " + stuTemp.getId());

	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// creating multiple student object
		System.out.println("Creating new multiple student");
		Student stuOne = new Student("Raman", "Sharma", "ramansharma@gmail.com");
		Student stuTwo = new Student("Harsh", "Tomar", "harshtomar@gmail.com");
		Student stuThree = new Student("Abhay", "Rajawat", "abhayrajawat@gmail.com");

		// saving multiple objects
		System.out.println("Saving multiple student");
		studentDAO.save(stuOne);
		studentDAO.save(stuTwo);
		studentDAO.save(stuThree);
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object

		System.out.println("Creating new student");
		Student stuTemp = new Student("Minnie", "Mouse", "minniemouse@gmail.com");

		// save the student object

		System.out.println("Saving the student");
		studentDAO.save(stuTemp);

		// display the id of saved student

		System.out.println("The id of the student which just got saved is : " + stuTemp.getId());

		// retrieve student based in the id : primary key

		System.out.println("Retrieving object of id : " + stuTemp.getId());
		Student myStudent = studentDAO.findById(stuTemp.getId());
		System.out.println("Found the student : " + myStudent);

	}
	
	private void queryForStudent(StudentDAO studentDAO) {
		// get list of students
		List<Student> allStudent = studentDAO.findAll();
		// printing students
		for (Student printStu : allStudent) {
			System.out.println(printStu);
		}
	}

	private void findingByLastName(StudentDAO studentDAO) {
		List<Student> byLastName = studentDAO.findByLastName("Mouse");
		for(Student stu : byLastName) {
			System.out.println(stu);
		}
	}

	private void updateTheStudent(StudentDAO studentDAO) {
		// get the student based on the id
		int stuId = 3;
		System.out.println("Getting student with id : " + stuId);

		Student stuToUpdate = studentDAO.findById(3);

		System.out.println("Updating Student");

		// changing first name of student to "Alpesh"
		stuToUpdate.setFirstName("Alpesh");
		studentDAO.update(stuToUpdate);
		
		// display updated student
		System.out.println("Updated student : " + stuToUpdate);
	}

	private void deleteTheStudent(StudentDAO studentDAO) {
		int stuId = 3;
		System.out.println("Deleting the student of id : " + stuId);
		studentDAO.delete(stuId);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int noOfStudentDeleted = studentDAO.deleteAll();
		System.out.println("Number of Students deleted : " + noOfStudentDeleted);
	}
}
