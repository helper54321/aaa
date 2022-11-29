package udemys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorting {
	
	//Tuti kell: 
	   //1.Bubble
	   //2.Selection
	   //3.Insertion
	   //5.Merge
	   //6.Quick
	   //7.Counting
	   //9.Bucket
	
	//Passz kell-e
	   //4.Shell
	   //8.Radix

	public static void main(String[] args) {
		int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
		int[] intArray2 = { 2, 5, 9, 8, 2, 8, 7, 10, 4, 3 }; // 7.-hez (counting)
		int[] intArray3 = { 4725, 4586, 1330, 8792, 1594, 5729 }; // 8.-hoz (radix)
		int[] intArray4 = { 54, 46, 83, 66, 95, 92, 43 }; // 9.-hez (bucket)
		
		//bubble(intArray);
		//selection(intArray);
		//insertion(intArray);
		//shell(intArray);
		//mergeSort(intArray, 0, intArray.length);
		//quick(intArray, 0, intArray.length);
		//counting(intArray2, 1, 10);
		//radix(intArray3, 10, 4);
		bucket(intArray4);

		for (int i = 0; i < intArray4.length; i++) {
			System.out.print(intArray4[i] + " ");
		}
	}

	// 1. Bubor�k

	public static void bubble(int[] intArray) {
		for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
			for (int i = 0; i < lastUnsortedIndex; i++) { // Csak az utols� rendezetlenig kell menni, a m�g�tte l�v�k m�r rendezve vannak
				if (intArray[i] > intArray[i + 1]) {
					swap(intArray, i, i + 1);
				}
			}
		}
	}

	public static void swap(int[] array, int i, int j) {

		if (i == j) {
			return;
		}

		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// 2. Kiv�laszt�sos

	public static void selection(int[] intArray) {
		for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
			int largest = 0;

			for (int i = 0; i <= lastUnsortedIndex; i++) { // A bubor�kn�l i-t hasonl�tottuk i+1-el, ez�rt nem <= volt ott
				if (intArray[i] > intArray[largest]) {
					largest = i;
				}
			}

			swap(intArray, largest, lastUnsortedIndex);
		}
	}

	// 3. Besz�r�sos

	public static void insertion(int[] intArray) {
		for (int firstUnsortedIndex = 1; firstUnsortedIndex < intArray.length; firstUnsortedIndex++) {
			int newElement = intArray[firstUnsortedIndex];

			int i;

			for (i = firstUnsortedIndex; i > 0 && intArray[i - 1] > newElement; i--) {
				intArray[i] = intArray[i - 1];
			}

			intArray[i] = newElement;
		}
	}

	// 4. Shell

	public static void shell(int[] intArray) {
		for (int gap = intArray.length / 2; gap > 0; gap /= 2) {

			for (int i = gap; i < intArray.length; i++) {
				int newElement = intArray[i];

				int j = i;

				while (j >= gap && intArray[j - gap] > newElement) {
					intArray[j] = intArray[j - gap];
					j -= gap;
				}

				intArray[j] = newElement;
			}
		}
	}

	// 5. �sszef�s�l�

	public static void mergeSort(int[] input, int start, int end) {

		// Vagyis a t�mb m�r csak 1 elem�, teljesen sz�t lett osztva
		if (end - start < 2) {
			return;
		}

		int mid = (start + end) / 2;
		mergeSort(input, start, mid); // Az�rt mid-ig, mert 1-el t�bbet adunk meg a z�r�n�l mint az index, l�nyeg�ben a r�szt�mb m�ret�t (ami ugye 0-t�l megy)
		mergeSort(input, mid, end);
		merge(input, start, mid, end);
	}

	public static void merge(int[] input, int start, int mid, int end) {

		//A mid - 1 index� a bal oldali r�szt�mb utols� eleme, a mid index pedig a jobb oldali els� eleme. 
		//Ebb�l k�vetkezik, hogy ha a bal oldalon l�v� legnagyobb kisebb, mint a jobb oldali legkisebb, akkor k�sz a rendez�s
		//�s mivel ennek a met�dusnak az egyetlen c�lja, hogy rendezze a bal �s jobb oldali (pl a 3 �s 4 elem� t�mb�ket, de lesz olyan is, amikor csak 2 db 1 elem�vel j�v�nk be, azokn�l sim�n lehet, hogy nem kell megcser�lni a sorrendet), emiatt ha m�r eleve rendezettek, akkor nincs is mit csin�lni, lehet returnolni
		if (input[mid - 1] <= input[mid]) {
			return;
		}

		int i = start;
		int j = mid;
		int tempIndex = 0;

		int[] temp = new int[end - start];

		while (i < mid && j < end) {
			temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++]; // Ha itt < lenne <= helyett, akkor nem lenne t�bb� stable rendez�s, mert akkor �rt�kegyenl�s�g eset�n el�sz�r a jobb oldalit �rn� be

		}

		//Itt m�r csak visszam�soljuk a bal oldalon esetlegesen megmaradt elemeket
		//Ha a jobb oldalon maradt meg, akkor semmit nem kell csin�lnunk, mert pl:
		//{32, 34} �s {33, 36} eset�n ha el��ll a {32, 33, 34} r�szeredm�ny, akkor a 36-ot ugyan be�rhatn�nk de felesleges mivel az eredetileg megkapott 2x2 elem� t�mbben, alapb�l a jobb oldali t�mb legjobboldalibb eleme volt, �s a v�g�n a visszam�sol�s ut�n ez szint�n nem v�ltozik (mivel ennek a met�dusnak csak az a c�lja, hogy megtudjuk melyik elem hova ker�l)
		//De ha pl {32, 36} �s {33, 34} lenne, akkor a {32, 33, 34} ut�n a 36 m�g nem a megfelel� hely�n lenne, mert amikor bej�tt�nk a 2. elem (1. index) volt, de a v�g�n a 4. elem (teh�t 3. indexen) kell lennie
		
		//Itt kezelj�k a bal oldali megmaradtakat (ha vannak). Teh�t ha a jobb oldalr�l van megmarad� elem ez semmit nem csin�l.
		//De m�r nem a temp-be, hanem az eredetibe, mivel tudjuk hogy ott melyik indexekbe ker�lnek
		//�s hogy �ssze ne zavarodjak: Itt az eredeti t�mb nem a teljes 7 elem� t�mb, hanem az a k�t r�szt�mb amivel �ppen bej�v�nk (teh�t lehet, hogy pl 2 db 2 elem�). A v�g�n ny�lv�n bej�v�nk az utols� l�p�ssel is, ahol m�r el��ll a teljes is, de el�tte sokszor j�v�nk kevesebb elemmel is)
		//Vagyis pontosabban fogalmazva: Az inputk�nt kapott t�mb igenis a teljes 7 elem�, de annak csak egy bizonyos tartom�ny�val dolgozunk (fentebb a start �s mid meghat�rozza melyikkel)
		System.arraycopy(input, i, input, start + tempIndex, mid - i);
		
		//Ez a r�sz az ami kihagyhat�, mert nem v�ltoztat semmin, de ha m�gis akarn�m akkor �gy k�ne
//		System.arraycopy(input, j, input, end - tempIndex, end - j);
		
		//Itt pedig nem a jobb oldalikat, hanem az el��llt rendezett temp t�mb�t (mivel seg�dt�mbbel dolgozunk), �tm�soljuk az eredetibe
		System.arraycopy(temp, 0, input, start, tempIndex);
	}

	// 6. Gyors

	public static void quick(int[] input, int start, int end) {
		if (end - start < 2) {
			return;
		}

		int pivotIndex = partition(input, start, end); //Az itt visszaadott index� elem m�r a megfelel� hely�re ker�l, ez�rt nem is adjuk �t lentebb (vagyis az els� quick-n�l l�tsz�lag igen, de mivel mindig a haszn�lni k�v�nt-n�l 1-el nagyobb indexet adunk, ez�rt azzal �gyse csin�l semmit)
		quick(input, start, pivotIndex); // Az�rt pivotindexig, �s nem pivot-1, mert mindig 1-el nagyobb �rt�ket adunk meg (a partitionban �gyis r�gt�n cs�kketj�k j-t, �gy nem lesz gond)
		quick(input, pivotIndex + 1, end); // Az�rt pivot+1, mert a pivot m�r mindig a hely�n lesz, azzal m�r nem kellessz foglalkozni
	}

	private static int partition(int[] input, int start, int end) {

		//Enn�l az implement�ci�n�l mindig a legels� elemet haszn�ljuk pivotk�nt (ami az �ppen esed�kes r�szt�mb els� eleme, teh�t nem felt�tlen�l 0-�s index)
		int pivot = input[start];
		
		int i = start;
		int j = end;

		//Az�rt �gy csin�ltuk, hogy egy k�ls� �s k�t bels�, mert mindig azt akarjuk, hogy amikor az itteni felt�tel szerint m�g folytathatjuk, akkor a bentebbi 2 while mindig lefusson.
		//Teh�t az els� while-ban nyugodtan fel�l�rhatjuk az i-edik indexet, mert a pivot tartalmazza az �rt�ket
		//A m�sodikban pedig fel�l�rhatjuk a j-ediket, mert az els� while-ban az m�r beker�lt az i-edik indexbe
		while (i < j) {

			// A t�rzs�ben nem csin�lunk semmit, csak a seg�dv�ltoz�k v�ltoztat�sa miatt kell
			//Az�rt cs�kkentj�k el�sz�r a (--)-al, mert az end �rt�ke mindig 1-el nagyobb az utols�nak haszn�lni k�v�ntn�l, teh�t en�lk�l a teljes t�mb eset�n indexarrayoutofbounds lenne, r�szt�mb eset�n pedig 1-el t�bb indexel dolgozn�nk mint akarunk
			//A k�s�bbi iter�ci�k eset�n pedig az�rt fontos, mert addigra a j-edik index� elem m�r feldolgoz�sra ker�lt
			while (i < j && input[--j] >= pivot);
			
			if (i < j) {
				input[i] = input[j];
			}
			
			

			// A t�rzs�ben nem csin�lunk semmit, csak a seg�dv�ltoz�k v�ltoztat�sa miatt kell
			//Itt pedig az�rt n�velj�k r�gt�n i �rt�k�t a (++)-al, mert maga az i kezd��rt�ke a pivot elem indexe, azt pedig �nmag�val nem akarjuk �sszehasonl�tani
			//A k�s�bbi iter�ci�k eset�n pedig az�rt fontos, mert addigra az i-edik index� elem m�r feldolgoz�sra ker�lt
			while (i < j && input[++i] <= pivot);
			
			//Enn�l nem lesz gond, hogy fel�l�rjuk a j-edik helyen l�v� elemet, mert a fentebbi if-n�l, az m�r �gyis beker�lt, egy i-edik helyre. Itt pedig j �rt�ke v�ltozatlan marad, csak i-t n�velt�k a while-ban 
			if (i < j) {
				input[j] = input[i];
			}
		}

		input[j] = pivot;
		return j; // Az index�t akarjuk visszaadni, nem az �rt�k�t
	}

	// 7. Lesz�mol�

	public static void counting(int[] input, int min, int max) {
		int[] countArray = new int[(max - min) + 1];

		for (int i = 0; i < input.length; i++) {
			// Pl az eredeti t�mb 2. eleme 5, �gy 5 - 1 = 4, teh�t a 4. index� hely �rt�k�t
			// kell n�velni (mivel ugye 0-t�l indexel a t�mb) (az�rt 1-et voltunk le, mert a
			// mi eset�nkben ez a minimum, egy m�sik p�ld�n�l m�s is lehetne)
			countArray[input[i] - min] += 1;
		}

		int j = 0;
		for (int i = min; i <= max; i++) {
			while (countArray[i - min] > 0) {
				input[j++] = i;
				countArray[i - min]--;
			}
		}
	}

	// 8. Sz�mjegyes

	public static void radix(int[] input, int radix, int width) {
		// Az i az�rt mehet 0-t�l, mert a getDigitn�l azt hat�rozzuk meg vele, hogy a
		// radixot (ami most 10), h�nyadik hatv�nyra emelj�k
		for (int i = 0; i < width; i++) {
			radixSingleSort(input, i, radix);
		}
	}

	public static void radixSingleSort(int[] input, int position, int radix) {

		int numItems = input.length;
		int[] countArray = new int[radix];

		for (int value : input) {
			// Ezzel m�g csak a hagyom�nyos countarray-t kapjuk meg, nem a m�dos�tott verzi�t.
			countArray[getDigit(position, value, radix)]++; // Minden elemn�l jobbr�l az i-edik (�ppen melyik), helyen l�v� sz�mot adja vissza, �gy a countarrayban azon a helyen l�v� �rt�ket 1-el n�velj�k
		}

		// Ezzel el��ll a m�dos�tott verzi�s counting array
		for (int j = 1; j < radix; j++) {
			countArray[j] += countArray[j - 1];
		}

		int[] temp = new int[numItems];
		for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--) {
			temp[--countArray[getDigit(position, input[tempIndex], radix)]] = input[tempIndex];
		}

		// Visszam�soljuk az ideiglenes t�mbb�l a rendezett elemeket az eredeti t�mbbe (sima for ciklussal is lehetne)
		System.arraycopy(temp, 0, input, 0, numItems);
	}

	public static int getDigit(int position, int value, int radix) {
		return value / (int) Math.pow(radix, position) % radix;
	}

	// 9. Bucket

	public static void bucket(int[] input) {
		List<Integer>[] buckets = new List[10];

		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<Integer>(); // vagy linkedlist, vector, t�kmindegy
		}

		for (int i = 0; i < input.length; i++) {
			buckets[hash(input[i])].add(input[i]);
		}

		for (List bucket : buckets) {
			Collections.sort(bucket);
		}

		int j = 0;
		for (int i = 0; i < buckets.length; i++) {
			for (int value : buckets[i]) {
				input[j++] = value;
			}
		}
	}

	private static int hash(int value) {
		return value / (int) 10; // value / (int) 10 % 10 is lehetne (de mivel itt mind2 sz�m 10, �gy a 2. r�sz nem sz�ks�ges, m�s sz�mokn�l kellhetne)
	}

}
