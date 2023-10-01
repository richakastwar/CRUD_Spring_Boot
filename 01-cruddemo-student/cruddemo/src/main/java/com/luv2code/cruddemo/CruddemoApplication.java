package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	Scanner sc = new Scanner(System.in);

	@Bean
	public CommandLineRunner CommandLineRunner(StudentDAO studentDAO){
		return runner -> {

			System.out.println("********************************************");
			System.out.println("Welcome to CRUD Application Using Spring Boot");
			System.out.println("********************************************");
			System.out.println();
			System.out.println("Enter your Choice :");
			System.out.println("1. For Create an object");
			System.out.println("2. For Create Multiple  objects");
			System.out.println("3. For Read an objects");
			System.out.println("4. For Update an object");
			System.out.println("5. For Delete an object");
			System.out.println("6. For Delete all objects");

			int choice = sc.nextInt();
			System.out.println();

			switch (choice){
				case 1:
					createStudent(studentDAO);
					break;

				case 2:
					createMultipleStudents(studentDAO);
					break;

				case 3:
					queryForStudents(studentDAO);
					break;

				case 4:
					updateStudent(studentDAO);
					break;

				case 5:
					deleteStudent(studentDAO);
					break;

				case 6:
					deleteAllStudents(studentDAO);
					break;

				default:
					System.out.println("Wrong Choice");

			}


			// createStudent(studentDAO);

			// createMultipleStudents(studentDAO);

//			 readStudent(studentDAO);

//			 queryForStudents(studentDAO);

			// queryForStudentsByLastName(studentDAO);

//			 updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting All Students ... ");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count "+numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		System.out.print("Enter your Student ID: ");
		int sId = sc.nextInt();
		System.out.println();

		System.out.println("Deleting student id "+sId);
		studentDAO.delete(sId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		// Enter Student ID from user
		System.out.print("Enter your Student ID: ");
		int sId = sc.nextInt();
		System.out.println();

		// retrieve student based on the id: primary key
		System.out.println("Getting student with id:"+sId);
		Student myStudent = studentDAO.findById(sId);

		System.out.print("Enter your First name to Update:");
		String fName = sc.next();
		System.out.println();

		// changing first name
		System.out.println("Updating student ...");
		myStudent.setFirstname(fName);

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: "+myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastname("ahmad");

		// display list of student
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}

	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of student
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("Creating new student object ... ");
		Student tempStudent = new Student("Bush","Maruti","bush@gmail.com");

		// save the student object
		System.out.println("Saving the student ... ");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student . Generated id: "+theId);

		// retrieving the student based on the id: primary key
		System.out.println("Retrieving the student with id "+theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the student: "+myStudent);

	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple student
		System.out.println("Creating 3 student objects ... ");
		Student tempStudent1 = new Student("Richa	","Kastwar","richakastwar@gmail.com");
		Student tempStudent2 = new Student("Abhishek","Namdev","abhinamdev360@gmail.com");
		Student tempStudent3 = new Student("Sahruk","Khan","srk@gmail.com");


		// save the student objects
		System.out.println("Saving the student ... ");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO) {

		// Enter the student details from user
		System.out.print("Enter your First Name: ");
		String fName = sc.next();

		System.out.print("Enter your Last Name: ");
		String lName = sc.next();

		System.out.print("Enter your Email: ");
		String email = sc.next();
		System.out.println();

		// create the student object
		System.out.println("Creating new student object ... ");
		Student tempStudent = new Student(fName,lName,email);

		// save the student object
		System.out.println("Saving the student ... ");
		studentDAO.save(tempStudent);

		// display id of saved student
		System.out.println("Saved student . Generated id: "+tempStudent.getId());
	}
}