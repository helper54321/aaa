package com.luv2code.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="youtube_channel")
	private String youtubeChannel;
	
	@Column(name="hobby")
	private String hobby;
	
	//�j mez� az instructor el�r�s�hez (ezt az adatb�zisba nem kell felvinni)
	//alapb�l, ahogy a hb-01-es projektn�l csin�ltuk el�sz�r le kellett k�rni az instructort, �s azon kereszt�l tudtuk el�rni a hozz�tartoz� instructordetailt
	//ezzel a kieg�sz�t�ssel visszafel� is m�k�dni fog (ez�rt bi-directional)
	//A mappedby-n�l az Insturctor classben l�v� foreign keyk�nt haszn�lt v�ltoz�t adjuk meg (mivel ann�l elmondjuk, hogy hogyan k�sse �ssze a t�bl�kat, �gy hogy hivatkozunk arra, lehet�s�g�nk lesz visszafel� is a lek�r�sre ahogy fentebb �rtam)
	
//	@OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
	//Ebben az esetben all helyett m�dos�tottunk, �gy egyed�l a t�rl�s eset�n (mivel azt kihagytuk a felsorol�sb�l) csak egyir�ny� lesz a m�velet. A hozz�kapcsol�d� instructor nem fog t�rl�dni
	@OneToOne(mappedBy = "instructorDetail", 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Instructor instructor;
	
	
	public InstructorDetail() {
		
	}

	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChannel=" + youtubeChannel + ", hobby=" + hobby + "]";
	}
	
	
	
	
	
}
