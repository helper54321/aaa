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
import javax.persistence.Table;


@Entity //Ezzel jelezzük, hogy hozzá fogunk rendelni egy táblát az adatbázisban
@Table(name="student") //Itt pedig megmondjuk a tábla nevét (ha a class és táblanév megegyezik ez a rész nem is kötelezõ)
public class Student {
	
	@Id //primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") //Megmondjuk, hogy a táblában melyik mezõ tartozik hozzá (csak akkor kötelezõ, ha a nevek nem egyeznek meg)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	, fetch=FetchType.LAZY)
	@JoinTable(
			name="course_student", //A kapcsoló tábla neve
			joinColumns = @JoinColumn(name="student_id"), //Mivel a student class-ben vagyok, ezért ide a student-hez tartozó foreign key kerül
			inverseJoinColumns=@JoinColumn(name="course_id") //Mivel nem a Course-ban vagyok, ezért az ahhoz tartozó lesz az inverse (másik class-ben nyílván fordítva lesz ez a 2)
	)
	private List<Course> courses;

	public Student() {
		
	}

	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course theCourse) {
		if(this.courses == null) {
			courses = new ArrayList<Course>();
		}
		
		this.courses.add(theCourse);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	
	
	
	
	
}
