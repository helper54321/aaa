package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class DeleteMaryStudentDemo {

	public static void main(String[] args) {

		//create session factory (csak 1x kell l�trehozni)
		SessionFactory factory =  new Configuration()
									.configure("hibernate.cfg.xml") //ahov� a kapcsol�d�shoz sz�ks�ges adatainkat megadtunk (ez a default n�v amin keresi, teh�t ha nem m�s neven van, akkor itt el�g az �res z�r�jel is)
									.addAnnotatedClass(Instructor.class) //Az a class, amit entityk�nt haszn�lunk
									.addAnnotatedClass(InstructorDetail.class) //Az a class, amit entityk�nt haszn�lunk
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory(); //l�trehozza a sessionfactoryt
		
		Session session = factory.getCurrentSession();
		
		try {
		
			session.beginTransaction();
			
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\n\nCourses: " + tempStudent.getCourses());
			
			session.delete(tempStudent);
			
			session.getTransaction().commit();
		
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
