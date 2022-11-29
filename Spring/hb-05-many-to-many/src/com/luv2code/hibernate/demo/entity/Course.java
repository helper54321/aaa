package com.luv2code.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	//A Joincolumn rész mindig a Many oldalra kerül
	//Logikusan belegondolva igaz is, hogy ha oda tenném amibõl csak 1 van, akkor hogy mutatna egyszerre mindegyikre ami hozzátartozik?
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	//Azért all, mivel logikusan belegondolva ha egy course-t törlünk, akkor a hozzátartozó review-eket is kell
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Review> reviews;
	
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
				, fetch=FetchType.LAZY)
	@JoinTable(
			name="course_student", //A kapcsoló tábla neve
			joinColumns = @JoinColumn(name="course_id"), //Mivel a course class-ben vagyok, ezért ide a course-hoz tartozó foreign key kerül
			inverseJoinColumns=@JoinColumn(name="student_id") //Mivel nem a Student-ben vagyok, ezért az ahhoz tartozó lesz az inverse (másik class-ben nyílván fordítva lesz ez a 2)
			)
	private List<Student> students;
	
	
	
	public Course() {
		
	}

	public Course(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void addReview(Review theReview) {
		if(this.reviews == null) {
			reviews = new ArrayList<Review>();
		}
		
		this.reviews.add(theReview);
	}
	
	public void addStudent(Student theStudent) {
		if(this.students == null) {
			students = new ArrayList<Student>();
		}
		
		students.add(theStudent);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

	
	
	
	
}
