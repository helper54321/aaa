package com.in28minutes.junit.helper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


//Ennek a classnek és az annotációknak a szerepe, hogy ha van sok teszt class-ünk,
//de csak néhányat akarunk belõlük futtatni, akkor itt megmondhatjuk, hogy melyikek legyenek azok
//Tehát a 2. annotációnál soroljuk fel, a futtatni akart classeket (lehetne 1 elemû is, de nem sok értelme lenne)
//Létrehozható így manuálisan is, vagy pedig new JUnit Test Suite (ahol a classeket is létrehozzuk, csak itt az others-be kell hozzá menni)
@RunWith(Suite.class)
@SuiteClasses({ QuickBeforeAfterTest.class, StringHelperTest.class })
public class AllTests {

}
