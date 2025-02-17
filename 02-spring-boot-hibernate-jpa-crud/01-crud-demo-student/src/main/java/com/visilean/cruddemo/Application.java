package com.visilean.cruddemo;

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
			// createStudent(studentDAO)
//			createMultipleStudent(studentDAO);
			readStudent(studentDAO);
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
		Student stuTemp = new Student("Mickey", "Mouse", "mickeymouse@gmail.com");

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

}
