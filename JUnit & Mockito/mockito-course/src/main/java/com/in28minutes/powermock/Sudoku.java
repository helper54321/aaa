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
		
		//A kezdeti tábla kiíratása
		printBoard(board);
		
		if(solveBoard(board)) {
			System.out.println("Solved successfully");
		}else {
			System.out.println("Unsolvable board");
		}
		
		//A kész tábla kiíratása
		printBoard(board);
		
		
	}
	
	
	private static void printBoard(int[][] board) {
		for(int row = 0; row < GRID_SIZE; row++) {
			
			//Ez és lentebb a column-os esetben is már csak az átláthatóság miatt a kiíratásnál, nem lenne kötelezõ
			//A != 0 vizsgálat csak azért kell, hogy a legelsõ sor fölé ne tegyen (mert nyílván ott is 0 lenne a % eredménye)
			if(row % 3 == 0 && row != 0) {
				System.out.println("-----------");
			}
			
			for(int column = 0; column < GRID_SIZE; column++) {
				if(column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				
				System.out.print(board[row][column]);
			}
			//Minden sor 9 száma után lépjen új sorba
			System.out.println();
		}
		
	}


	//Row 0-8 között lehet, mivel array indexként kezeljük
	//Egyszerre csak egy adott sorban nézi ez a metódus, nem megy végig minden
	private static boolean isNumberInRow(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[row][i] == number){
				return true;
			}
		}
		
		return false;
	}
	
	
	//Majdnem ugyanaz, mint a sor esetén, csak itt nyílván oszlopban nézi
	private static boolean isNumberInColumn(int[][] board, int number, int column) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[i][column] == number){
				return true;
			}
		}
		
		return false;
	}
	
	
	//Minta szemléltetéshez
	//0|5|0		0|5|0
	//0|0|3		0|1|3
	//0|0|9		0|0|9
	//Ezzel azt akarjuk megnézni, hogy az adott 3x3-as részben sem szerepel-e
	//A fentebbi szemléltetés esetén a középsõ sor középsõ oszlopába próbáljuk az 1-est beszúrni
	//Ahhoz hogy meg tudjuk mondani, hogy szerepel-e már a 3x3-as részben, meg kell tudnunk a 3x3-as rész kezdõ indexeit (tehát hányadik sor és oszlop indexnél kezdõdik)
	private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
		
		//Megadja a sor kezdõindexét
		int localBoxRow = row - row % 3;
		
		//Ugyanaz csak az oszlopra
		int localBoxColumn = column - column % 3;
		
		//Itt vizsgáljuk a 3x3-as részt
		for(int i = localBoxRow; i < localBoxRow + 3; i++) {
			for(int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if(board[i][j] == number) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	//A fenti 3 metódus felhasználásával ellenõrizzük, hogy tehetõ-e oda a szám
	private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
		
		//Ha mindegyik false-t ad vissza, akkor megkapjuk, hogy helyezhetõ oda a szám
		return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInBox(board, number, row, column);
	}
	
	
	private static boolean solveBoard(int[][] board) {
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int column = 0; column < GRID_SIZE; column++) {
				
				//Mivel 0-ával jelöltük a még kitöltetlen helyeket, tehát itt vizsgálja, hogy az-e
				if(board[row][column] == 0) {
					
					//A talált üres helyen 1-9-ig elkezdjük végignézni, amíg nem találunk egy olyan számot, ami sikeresen beilleszthetõ oda
					for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if(isValidPlacement(board, numberToTry, row, column)) {
							board[row][column] = numberToTry;
							
							//Ha sikeresen beillesztettünk egy számot, akkor újra meghívjuk rekurzívan, ami megint elõrõl kezdi vizsgálni üres helyért, de itt a korábban üresen lévõ helyére már lesz beírva, így az utána lévõ üres helyet nézi
							//Végül ha az egész ki lett töltve, akkor már nem kell újra belépni, hanem visszaadhatjuk a true-t
							if(solveBoard(board)){
								return true;
							}else {
								//Ha semmit nem tudtunk beírni, mert mindegyik ütközne, akkor tegyük vissza 0-ra, és lépjünk vissza az elõzõ üres mezõre (ami kezdetben üres volt, nyílván itt már nem lenne)
								//Rekurzívan elkezd visszamenegetni, és mindenhova beírja a 0-át ahová szükséges (nyílván egy beírás után az amögötti részt újból megpróbálja megcsinálni, az egészet, ami csak akkor sikerül ha minden jól lett beírva)
								board[row][column] = 0;
							}
						}
					}
					
					//Abban az esetben hajtódik végre, ha talált egy üres helyet, de oda már semmit nem tud beírni, mert a korábban beírt számok nem jól lettek beírva
					//Ilyenkor szükséges lesz visszalépni, az elõzõleg beírt számra, és ott a következõ beírható számért keresni. Ha tudunk oké, ha nem, akkor még egy lépéssel visszamegyünk, míg végül elõáll a kész tábla (rengeteg lépés lesz, de mivel a gépnek nem számít így oké)
					//Ennél a lépésnél nem feltétlenül kell a [8][8], tehát a legutolsó helyen lenni, elõfordulhat, hogy pl akár már a [5][2] helyre se tudunk mit írni, akkor már nincs is értelme tovább folytatni, vissza kell lépni, mert a korábbi kitöltés nem volt jó
					//Tehát ez akkor hajtódik végre, amikor már a jelenlegi állás szerint nincs értelme folytatni, mert a korábbi beírások nem jók
					return false;
				}
			}
		}
		
		//Ez az a true, ahova minden egyes sikeres beírás után eljut, a fentebbi true akkor lesz, ha teljesen kész vagyunk
		return true;
	}
}
