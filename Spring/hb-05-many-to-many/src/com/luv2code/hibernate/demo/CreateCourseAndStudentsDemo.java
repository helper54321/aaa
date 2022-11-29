package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		//create session factory (csak 1x kell létrehozni)
		SessionFactory factory =  new Configuration()
									.configure("hibernate.cfg.xml") //ahová a kapcsolódáshoz szükséges adatainkat megadtunk (ez a default név amin keresi, tehát ha nem más neven van, akkor itt elég az üres zárójel is)
									.addAnnotatedClass(Instructor.class) //Az a class, amit entityként használunk
									.addAnnotatedClass(InstructorDetail.class) //Az a class, amit entityként használunk
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory(); //létrehozza a sessionfactoryt
		
		Session session = factory.getCurrentSession();
		
		try {
		
			session.beginTransaction();
			
			Course tempCourse = new Course("Pacman - How to score one million points");
			
			
			System.out.println("\nSaving the course...");
			session.save(tempCourse);
			System.out.println("\nCourse saved: " + tempCourse);
			
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			System.out.println("\nSaving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\nSaved students: " + tempCourse.getStudents());
			
			
			session.getTransaction().commit();
		
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
