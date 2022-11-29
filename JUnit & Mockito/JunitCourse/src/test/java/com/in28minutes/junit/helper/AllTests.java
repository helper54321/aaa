package com.in28minutes.junit.helper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


//Ennek a classnek �s az annot�ci�knak a szerepe, hogy ha van sok teszt class-�nk,
//de csak n�h�nyat akarunk bel�l�k futtatni, akkor itt megmondhatjuk, hogy melyikek legyenek azok
//Teh�t a 2. annot�ci�n�l soroljuk fel, a futtatni akart classeket (lehetne 1 elem� is, de nem sok �rtelme lenne)
//L�trehozhat� �gy manu�lisan is, vagy pedig new JUnit Test Suite (ahol a classeket is l�trehozzuk, csak itt az others-be kell hozz� menni)
@RunWith(Suite.class)
@SuiteClasses({ QuickBeforeAfterTest.class, StringHelperTest.class })
public class AllTests {

}
