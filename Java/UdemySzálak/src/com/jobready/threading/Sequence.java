package com.jobready.threading;

public class Sequence {
	
	private int value = 0;
	
//	public int getNext() {
//		
//		//Ennek a tartalma vagy eg�szben v�grehajt�dik megszak�t�s n�lk�l vagy egy�ltal�n nem
//		//Magyarul csak 1 sz�l haszn�lhatja egyszerre (ameddig az nem v�gzett m�sik nem haszn�lhatja)
//		synchronized (this){
//			value++;
//			return value;
//		}
//		
//	}
	
	
	//Ugyanaz mint a fenti, de r�videbb k�d
	public synchronized int getNext() {
			value++;
			return value;
		}

}
