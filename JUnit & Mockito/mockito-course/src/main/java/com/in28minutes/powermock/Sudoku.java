package com.in28minutes.powermock;

import java.util.Iterator;

public class Sudoku {
	
	private static final int GRID_SIZE = 9;

	public static void main(String[] args) {
	
		int [][] board = {
				{7, 0, 2, 0, 5, 0, 6, 0, 0},
				{0, 0, 0, 0, 0, 3, 0, 0, 0},
				{1, 0, 0, 0, 0, 9, 5, 0, 0},
				{8, 0, 0, 0, 0, 0, 0, 9, 0},
				{0, 4, 3, 0, 0, 0, 7, 5, 0},
				{0, 9, 0, 0, 0, 0, 0, 0, 8},
				{0, 0, 9, 7, 0, 0, 0, 0, 5},
				{0, 0, 0, 2, 0, 0, 0, 0, 0},
				{0, 0, 7, 0, 4, 0, 2, 0, 3}
		};
		
		//A kezdeti t�bla ki�rat�sa
		printBoard(board);
		
		if(solveBoard(board)) {
			System.out.println("Solved successfully");
		}else {
			System.out.println("Unsolvable board");
		}
		
		//A k�sz t�bla ki�rat�sa
		printBoard(board);
		
		
	}
	
	
	private static void printBoard(int[][] board) {
		for(int row = 0; row < GRID_SIZE; row++) {
			
			//Ez �s lentebb a column-os esetben is m�r csak az �tl�that�s�g miatt a ki�rat�sn�l, nem lenne k�telez�
			//A != 0 vizsg�lat csak az�rt kell, hogy a legels� sor f�l� ne tegyen (mert ny�lv�n ott is 0 lenne a % eredm�nye)
			if(row % 3 == 0 && row != 0) {
				System.out.println("-----------");
			}
			
			for(int column = 0; column < GRID_SIZE; column++) {
				if(column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				
				System.out.print(board[row][column]);
			}
			//Minden sor 9 sz�ma ut�n l�pjen �j sorba
			System.out.println();
		}
		
	}


	//Row 0-8 k�z�tt lehet, mivel array indexk�nt kezelj�k
	//Egyszerre csak egy adott sorban n�zi ez a met�dus, nem megy v�gig minden
	private static boolean isNumberInRow(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[row][i] == number){
				return true;
			}
		}
		
		return false;
	}
	
	
	//Majdnem ugyanaz, mint a sor eset�n, csak itt ny�lv�n oszlopban n�zi
	private static boolean isNumberInColumn(int[][] board, int number, int column) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[i][column] == number){
				return true;
			}
		}
		
		return false;
	}
	
	
	//Minta szeml�ltet�shez
	//0|5|0		0|5|0
	//0|0|3		0|1|3
	//0|0|9		0|0|9
	//Ezzel azt akarjuk megn�zni, hogy az adott 3x3-as r�szben sem szerepel-e
	//A fentebbi szeml�ltet�s eset�n a k�z�ps� sor k�z�ps� oszlop�ba pr�b�ljuk az 1-est besz�rni
	//Ahhoz hogy meg tudjuk mondani, hogy szerepel-e m�r a 3x3-as r�szben, meg kell tudnunk a 3x3-as r�sz kezd� indexeit (teh�t h�nyadik sor �s oszlop indexn�l kezd�dik)
	private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
		
		//Megadja a sor kezd�index�t
		int localBoxRow = row - row % 3;
		
		//Ugyanaz csak az oszlopra
		int localBoxColumn = column - column % 3;
		
		//Itt vizsg�ljuk a 3x3-as r�szt
		for(int i = localBoxRow; i < localBoxRow + 3; i++) {
			for(int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if(board[i][j] == number) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	//A fenti 3 met�dus felhaszn�l�s�val ellen�rizz�k, hogy tehet�-e oda a sz�m
	private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
		
		//Ha mindegyik false-t ad vissza, akkor megkapjuk, hogy helyezhet� oda a sz�m
		return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInBox(board, number, row, column);
	}
	
	
	private static boolean solveBoard(int[][] board) {
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int column = 0; column < GRID_SIZE; column++) {
				
				//Mivel 0-�val jel�lt�k a m�g kit�ltetlen helyeket, teh�t itt vizsg�lja, hogy az-e
				if(board[row][column] == 0) {
					
					//A tal�lt �res helyen 1-9-ig elkezdj�k v�gign�zni, am�g nem tal�lunk egy olyan sz�mot, ami sikeresen beilleszthet� oda
					for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if(isValidPlacement(board, numberToTry, row, column)) {
							board[row][column] = numberToTry;
							
							//Ha sikeresen beillesztett�nk egy sz�mot, akkor �jra megh�vjuk rekurz�van, ami megint el�r�l kezdi vizsg�lni �res hely�rt, de itt a kor�bban �resen l�v� hely�re m�r lesz be�rva, �gy az ut�na l�v� �res helyet n�zi
							//V�g�l ha az eg�sz ki lett t�ltve, akkor m�r nem kell �jra bel�pni, hanem visszaadhatjuk a true-t
							if(solveBoard(board)){
								return true;
							}else {
								//Ha semmit nem tudtunk be�rni, mert mindegyik �tk�zne, akkor tegy�k vissza 0-ra, �s l�pj�nk vissza az el�z� �res mez�re (ami kezdetben �res volt, ny�lv�n itt m�r nem lenne)
								//Rekurz�van elkezd visszamenegetni, �s mindenhova be�rja a 0-�t ahov� sz�ks�ges (ny�lv�n egy be�r�s ut�n az am�g�tti r�szt �jb�l megpr�b�lja megcsin�lni, az eg�szet, ami csak akkor siker�l ha minden j�l lett be�rva)
								board[row][column] = 0;
							}
						}
					}
					
					//Abban az esetben hajt�dik v�gre, ha tal�lt egy �res helyet, de oda m�r semmit nem tud be�rni, mert a kor�bban be�rt sz�mok nem j�l lettek be�rva
					//Ilyenkor sz�ks�ges lesz visszal�pni, az el�z�leg be�rt sz�mra, �s ott a k�vetkez� be�rhat� sz�m�rt keresni. Ha tudunk ok�, ha nem, akkor m�g egy l�p�ssel visszamegy�nk, m�g v�g�l el��ll a k�sz t�bla (rengeteg l�p�s lesz, de mivel a g�pnek nem sz�m�t �gy ok�)
					//Enn�l a l�p�sn�l nem felt�tlen�l kell a [8][8], teh�t a legutols� helyen lenni, el�fordulhat, hogy pl ak�r m�r a [5][2] helyre se tudunk mit �rni, akkor m�r nincs is �rtelme tov�bb folytatni, vissza kell l�pni, mert a kor�bbi kit�lt�s nem volt j�
					//Teh�t ez akkor hajt�dik v�gre, amikor m�r a jelenlegi �ll�s szerint nincs �rtelme folytatni, mert a kor�bbi be�r�sok nem j�k
					return false;
				}
			}
		}
		
		//Ez az a true, ahova minden egyes sikeres be�r�s ut�n eljut, a fentebbi true akkor lesz, ha teljesen k�sz vagyunk
		return true;
	}
}
