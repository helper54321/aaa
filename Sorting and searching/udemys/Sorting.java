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

	// 1. Buborék

	public static void bubble(int[] intArray) {
		for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
			for (int i = 0; i < lastUnsortedIndex; i++) { // Csak az utolsó rendezetlenig kell menni, a mögötte lévõk már rendezve vannak
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

	// 2. Kiválasztásos

	public static void selection(int[] intArray) {
		for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
			int largest = 0;

			for (int i = 0; i <= lastUnsortedIndex; i++) { // A buboréknál i-t hasonlítottuk i+1-el, ezért nem <= volt ott
				if (intArray[i] > intArray[largest]) {
					largest = i;
				}
			}

			swap(intArray, largest, lastUnsortedIndex);
		}
	}

	// 3. Beszúrásos

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

	// 5. Összefésülõ

	public static void mergeSort(int[] input, int start, int end) {

		// Vagyis a tömb már csak 1 elemû, teljesen szét lett osztva
		if (end - start < 2) {
			return;
		}

		int mid = (start + end) / 2;
		mergeSort(input, start, mid); // Azért mid-ig, mert 1-el többet adunk meg a zárónál mint az index, lényegében a résztömb méretét (ami ugye 0-tól megy)
		mergeSort(input, mid, end);
		merge(input, start, mid, end);
	}

	public static void merge(int[] input, int start, int mid, int end) {

		//A mid - 1 indexû a bal oldali résztömb utolsó eleme, a mid index pedig a jobb oldali elsõ eleme. 
		//Ebbõl következik, hogy ha a bal oldalon lévõ legnagyobb kisebb, mint a jobb oldali legkisebb, akkor kész a rendezés
		//És mivel ennek a metódusnak az egyetlen célja, hogy rendezze a bal és jobb oldali (pl a 3 és 4 elemû tömböket, de lesz olyan is, amikor csak 2 db 1 elemûvel jövünk be, azoknál simán lehet, hogy nem kell megcserélni a sorrendet), emiatt ha már eleve rendezettek, akkor nincs is mit csinálni, lehet returnolni
		if (input[mid - 1] <= input[mid]) {
			return;
		}

		int i = start;
		int j = mid;
		int tempIndex = 0;

		int[] temp = new int[end - start];

		while (i < mid && j < end) {
			temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++]; // Ha itt < lenne <= helyett, akkor nem lenne többé stable rendezés, mert akkor értékegyenlõség esetén elõször a jobb oldalit írná be

		}

		//Itt már csak visszamásoljuk a bal oldalon esetlegesen megmaradt elemeket
		//Ha a jobb oldalon maradt meg, akkor semmit nem kell csinálnunk, mert pl:
		//{32, 34} és {33, 36} esetén ha elõáll a {32, 33, 34} részeredmény, akkor a 36-ot ugyan beírhatnánk de felesleges mivel az eredetileg megkapott 2x2 elemû tömbben, alapból a jobb oldali tömb legjobboldalibb eleme volt, és a végén a visszamásolás után ez szintén nem változik (mivel ennek a metódusnak csak az a célja, hogy megtudjuk melyik elem hova kerül)
		//De ha pl {32, 36} és {33, 34} lenne, akkor a {32, 33, 34} után a 36 még nem a megfelelõ helyén lenne, mert amikor bejöttünk a 2. elem (1. index) volt, de a végén a 4. elem (tehát 3. indexen) kell lennie
		
		//Itt kezeljük a bal oldali megmaradtakat (ha vannak). Tehát ha a jobb oldalról van megmaradó elem ez semmit nem csinál.
		//De már nem a temp-be, hanem az eredetibe, mivel tudjuk hogy ott melyik indexekbe kerülnek
		//És hogy össze ne zavarodjak: Itt az eredeti tömb nem a teljes 7 elemû tömb, hanem az a két résztömb amivel éppen bejövünk (tehát lehet, hogy pl 2 db 2 elemû). A végén nyílván bejövünk az utolsó lépéssel is, ahol már elõáll a teljes is, de elõtte sokszor jövünk kevesebb elemmel is)
		//Vagyis pontosabban fogalmazva: Az inputként kapott tömb igenis a teljes 7 elemû, de annak csak egy bizonyos tartományával dolgozunk (fentebb a start és mid meghatározza melyikkel)
		System.arraycopy(input, i, input, start + tempIndex, mid - i);
		
		//Ez a rész az ami kihagyható, mert nem változtat semmin, de ha mégis akarnám akkor így kéne
//		System.arraycopy(input, j, input, end - tempIndex, end - j);
		
		//Itt pedig nem a jobb oldalikat, hanem az elõállt rendezett temp tömböt (mivel segédtömbbel dolgozunk), átmásoljuk az eredetibe
		System.arraycopy(temp, 0, input, start, tempIndex);
	}

	// 6. Gyors

	public static void quick(int[] input, int start, int end) {
		if (end - start < 2) {
			return;
		}

		int pivotIndex = partition(input, start, end); //Az itt visszaadott indexû elem már a megfelelõ helyére kerül, ezért nem is adjuk át lentebb (vagyis az elsõ quick-nél látszólag igen, de mivel mindig a használni kívánt-nál 1-el nagyobb indexet adunk, ezért azzal úgyse csinál semmit)
		quick(input, start, pivotIndex); // Azért pivotindexig, és nem pivot-1, mert mindig 1-el nagyobb értéket adunk meg (a partitionban úgyis rögtön csökketjük j-t, így nem lesz gond)
		quick(input, pivotIndex + 1, end); // Azért pivot+1, mert a pivot már mindig a helyén lesz, azzal már nem kellessz foglalkozni
	}

	private static int partition(int[] input, int start, int end) {

		//Ennél az implementációnál mindig a legelsõ elemet használjuk pivotként (ami az éppen esedékes résztömb elsõ eleme, tehát nem feltétlenül 0-ás index)
		int pivot = input[start];
		
		int i = start;
		int j = end;

		//Azért így csináltuk, hogy egy külsõ és két belsõ, mert mindig azt akarjuk, hogy amikor az itteni feltétel szerint még folytathatjuk, akkor a bentebbi 2 while mindig lefusson.
		//Tehát az elsõ while-ban nyugodtan felülírhatjuk az i-edik indexet, mert a pivot tartalmazza az értéket
		//A másodikban pedig felülírhatjuk a j-ediket, mert az elsõ while-ban az már bekerült az i-edik indexbe
		while (i < j) {

			// A törzsében nem csinálunk semmit, csak a segédváltozók változtatása miatt kell
			//Azért csökkentjük elõször a (--)-al, mert az end értéke mindig 1-el nagyobb az utolsónak használni kívántnál, tehát enélkül a teljes tömb esetén indexarrayoutofbounds lenne, résztömb esetén pedig 1-el több indexel dolgoznánk mint akarunk
			//A késõbbi iterációk esetén pedig azért fontos, mert addigra a j-edik indexû elem már feldolgozásra került
			while (i < j && input[--j] >= pivot);
			
			if (i < j) {
				input[i] = input[j];
			}
			
			

			// A törzsében nem csinálunk semmit, csak a segédváltozók változtatása miatt kell
			//Itt pedig azért növeljük rögtön i értékét a (++)-al, mert maga az i kezdõértéke a pivot elem indexe, azt pedig önmagával nem akarjuk összehasonlítani
			//A késõbbi iterációk esetén pedig azért fontos, mert addigra az i-edik indexû elem már feldolgozásra került
			while (i < j && input[++i] <= pivot);
			
			//Ennél nem lesz gond, hogy felülírjuk a j-edik helyen lévõ elemet, mert a fentebbi if-nél, az már úgyis bekerült, egy i-edik helyre. Itt pedig j értéke változatlan marad, csak i-t növeltük a while-ban 
			if (i < j) {
				input[j] = input[i];
			}
		}

		input[j] = pivot;
		return j; // Az indexét akarjuk visszaadni, nem az értékét
	}

	// 7. Leszámoló

	public static void counting(int[] input, int min, int max) {
		int[] countArray = new int[(max - min) + 1];

		for (int i = 0; i < input.length; i++) {
			// Pl az eredeti tömb 2. eleme 5, így 5 - 1 = 4, tehát a 4. indexû hely értékét
			// kell növelni (mivel ugye 0-tól indexel a tömb) (azért 1-et voltunk le, mert a
			// mi esetünkben ez a minimum, egy másik példánál más is lehetne)
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

	// 8. Számjegyes

	public static void radix(int[] input, int radix, int width) {
		// Az i azért mehet 0-tól, mert a getDigitnél azt határozzuk meg vele, hogy a
		// radixot (ami most 10), hányadik hatványra emeljük
		for (int i = 0; i < width; i++) {
			radixSingleSort(input, i, radix);
		}
	}

	public static void radixSingleSort(int[] input, int position, int radix) {

		int numItems = input.length;
		int[] countArray = new int[radix];

		for (int value : input) {
			// Ezzel még csak a hagyományos countarray-t kapjuk meg, nem a módosított verziót.
			countArray[getDigit(position, value, radix)]++; // Minden elemnél jobbról az i-edik (éppen melyik), helyen lévõ számot adja vissza, így a countarrayban azon a helyen lévõ értéket 1-el növeljük
		}

		// Ezzel elõáll a módosított verziós counting array
		for (int j = 1; j < radix; j++) {
			countArray[j] += countArray[j - 1];
		}

		int[] temp = new int[numItems];
		for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--) {
			temp[--countArray[getDigit(position, input[tempIndex], radix)]] = input[tempIndex];
		}

		// Visszamásoljuk az ideiglenes tömbbõl a rendezett elemeket az eredeti tömbbe (sima for ciklussal is lehetne)
		System.arraycopy(temp, 0, input, 0, numItems);
	}

	public static int getDigit(int position, int value, int radix) {
		return value / (int) Math.pow(radix, position) % radix;
	}

	// 9. Bucket

	public static void bucket(int[] input) {
		List<Integer>[] buckets = new List[10];

		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<Integer>(); // vagy linkedlist, vector, tökmindegy
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
		return value / (int) 10; // value / (int) 10 % 10 is lehetne (de mivel itt mind2 szám 10, így a 2. rész nem szükséges, más számoknál kellhetne)
	}

}
