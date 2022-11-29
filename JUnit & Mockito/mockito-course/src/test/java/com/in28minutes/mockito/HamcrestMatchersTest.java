package com.in28minutes.mockito;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void test() {
		List<Integer> scores = Arrays.asList(99, 100, 101, 105);
		
		assertThat(scores, hasSize(4));
		
		//Ezeket mindet tartalmaznia kell a listának (más is lehet benne, de ezeknek muszáj)
		assertThat(scores, hasItems(99, 100)); 
		
		//Minden benne lévõ elem legyen 90-nél nagyobb (egyenlõség itt nem megengedett)
		//Ha egyenlõséget is megengedjük akkor másik metódus kell (a Matcher classben érdemes lehet szétnézni, ctrl + o-val ki tudjuk listázni a metódusneveket)
		assertThat(scores, everyItem(greaterThan(90)));
		
		assertThat(scores, everyItem(lessThan(190)));
		
		assertThat("", isEmptyString());
		
		//Üres string vagy null is megengedett
		assertThat(null, isEmptyOrNullString());
		
		
		Integer[] marks = {1, 2, 3};
		
		assertThat(marks, arrayWithSize(3));
		
		//Minden elemet meg kell adni, és pontos egyezõséget vizsgál, tehát nem azt hogy ezek benne vannak-e, hanem hogy pontosan ez a tartalma
		//Ennél a tartalmán kívül még a sorrend is kötelezõ, hiába egyeznek az elemek ha más a sorrend elbukik
		assertThat(marks, arrayContaining(1, 2, 3));
		
		//Ugyanaz mint fentebb, de itt már bármilyen sorrendben elfogadja, amíg a tartalmuk megegyezik
		assertThat(marks, arrayContainingInAnyOrder(2, 1, 3));
	}

}
