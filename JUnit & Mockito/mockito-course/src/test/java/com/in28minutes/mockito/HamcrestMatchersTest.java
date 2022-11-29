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
		
		//Ezeket mindet tartalmaznia kell a list�nak (m�s is lehet benne, de ezeknek musz�j)
		assertThat(scores, hasItems(99, 100)); 
		
		//Minden benne l�v� elem legyen 90-n�l nagyobb (egyenl�s�g itt nem megengedett)
		//Ha egyenl�s�get is megengedj�k akkor m�sik met�dus kell (a Matcher classben �rdemes lehet sz�tn�zni, ctrl + o-val ki tudjuk list�zni a met�dusneveket)
		assertThat(scores, everyItem(greaterThan(90)));
		
		assertThat(scores, everyItem(lessThan(190)));
		
		assertThat("", isEmptyString());
		
		//�res string vagy null is megengedett
		assertThat(null, isEmptyOrNullString());
		
		
		Integer[] marks = {1, 2, 3};
		
		assertThat(marks, arrayWithSize(3));
		
		//Minden elemet meg kell adni, �s pontos egyez�s�get vizsg�l, teh�t nem azt hogy ezek benne vannak-e, hanem hogy pontosan ez a tartalma
		//Enn�l a tartalm�n k�v�l m�g a sorrend is k�telez�, hi�ba egyeznek az elemek ha m�s a sorrend elbukik
		assertThat(marks, arrayContaining(1, 2, 3));
		
		//Ugyanaz mint fentebb, de itt m�r b�rmilyen sorrendben elfogadja, am�g a tartalmuk megegyezik
		assertThat(marks, arrayContainingInAnyOrder(2, 1, 3));
	}

}
