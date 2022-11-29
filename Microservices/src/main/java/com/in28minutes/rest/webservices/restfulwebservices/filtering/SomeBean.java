package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Itt felsoroljuk, hogy mi ne legyen benne a visszaadott json-ben, vagy lentebb a JsonIgnore egyesével is megtehetjük
//A lentebbi megoldás a javasolt, mert itt a változóneveket is meg kell nevezni, amik változhatnak
//@JsonIgnoreProperties(value = {"field1", "field2"})

//A másik classben létrehozott filterre hivatkozunk
@JsonFilter("SomeBeanFilter")
public class SomeBean {

	private String field1;
	private String field2;
	
	//Ezt a mezőt nem akarjuk visszaadni getmapping esetén (ebben a példában), ezért használunk filteringet
	//Ha több változót is ki akarunk hagyni, akkor a fentebbi @JsonIgnoreProperties is használható, és ott egyben megmondjuk őket
//	@JsonIgnore
	private String field3;
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	
	public String getField1() {
		return field1;
	}
	
	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	public String getField2() {
		return field2;
	}
	
	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	public String getField3() {
		return field3;
	}
	
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
	

	
}
