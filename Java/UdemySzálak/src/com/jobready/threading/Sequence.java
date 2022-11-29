package com.jobready.threading;

public class Sequence {
	
	private int value = 0;
	
//	public int getNext() {
//		
//		//Ennek a tartalma vagy egészben végrehajtódik megszakítás nélkül vagy egyáltalán nem
//		//Magyarul csak 1 szál használhatja egyszerre (ameddig az nem végzett másik nem használhatja)
//		synchronized (this){
//			value++;
//			return value;
//		}
//		
//	}
	
	
	//Ugyanaz mint a fenti, de rövidebb kód
	public synchronized int getNext() {
			value++;
			return value;
		}

}
