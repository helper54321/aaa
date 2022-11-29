package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

public class ArraysCompareTest {

	@Test
	public void testArraySort_RandomArray() {
		int[] numbers = {12, 3, 4, 1};
		Arrays.sort(numbers);
		
		assertArrayEquals(new int[] {1, 3, 4, 12}, numbers);
	}
	
	//Itt megmondjuk, hogy milyen exceptionra számítunk, és amiatt ne bukjon a teszt
	//Ebben az esetben olyannyira elvárja, hogy épp akkor bukik el a teszt ha nem dob exceptiont
	@Test(expected = NullPointerException.class)
	public void testArraySort_NullArray() {
		int[] numbers = null;
		Arrays.sort(numbers);
	}
	
	//Ez teljesítménybeli teszt, ahol a timeout-tal megmondjuk, hogy mennyi ms (1000ms = 1s) alatt kell teljesülnie a sikeres teszthez
	@Test(timeout = 100)
	public void testSort_Performance() {
		int[] array = {12, 23, 4};
		
		for (int i = 0; i <= 1000000; i++) {
			array[0] = i;
			Arrays.sort(array);
		}
	}

}
