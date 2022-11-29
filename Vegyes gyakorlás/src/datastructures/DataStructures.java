package datastructures;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class DataStructures {

	public static void main(String[] args) {
		
		mapes();
		
		
	}
	
	public static void arrayListes() {
		ArrayList<Person> arrayList = new ArrayList<>();
		Person person1 = new Person(1, "Vezetek1", "Kereszt1");
		Person person2 = new Person(2, "Vezetek2", "Kereszt2");
		arrayList.add(person1);
		arrayList.add(person2);
		
		System.out.println("Tartalmazza: " + arrayList.contains(person1)); //hashcode �s equals fel�l�r�s n�lk�l is true
		
		System.out.println("Foreach el�tt---------------");
		arrayList.forEach(person -> System.out.println(person));
		
		System.out.println("Iterator el�tt--------------");
		Iterator<Person> it = arrayList.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		
		arrayList.removeIf(Person.myPredicate());
		
		System.out.println("RemoveIf ut�n");
		arrayList.forEach(person -> System.out.println(person));
		
		Object[] arr = arrayList.toArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.println("T�mb form�ban: " + (arr[i] instanceof Person));
		}

		
		arrayList.add(person2);
		
		//utols� elem t�rl�se
		arrayList.remove(arrayList.size() - 1);
		System.out.println("Utols� elem t�rl�se ut�n");
		
		arrayList.forEach(person -> System.out.println(person));
		
		
		//mind2 lehets�ges
		ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
		
		//hozz�tarotoz� ciklus
		for(ArrayList<Integer> item : list2) {
			for(int i : item) {
				
			}
		}
		
		ArrayList<Integer[]> list3 = new ArrayList<Integer[]>();
		
		//hozz�tarotoz� ciklus
		for(Integer[] item : list3) {
			for(int i : item) {
				
			}
		}
		
	}
	
	public static void linkedListes() {
		LinkedList<Person> linkedList = new LinkedList<>();
		Person person1 = new Person(1, "Vezetek1", "Kereszt1");
		Person person2 = new Person(2, "Vezetek2", "Kereszt2");
		Person person3 = new Person(3, "Vezetek3", "Kereszt3");
		linkedList.add(person1);
		linkedList.add(person2);
		linkedList.add(person3);
		
		
		linkedList.forEach(person -> System.out.println(person));
		
		//A legels�k�nt hozz�adott elemet t�rli
		linkedList.pop();
		
		//utols� elem t�rl�se ut�n
		System.out.println("Utols� elem t�rl�se ut�n");
		linkedList.forEach(person -> System.out.println(person));
		
		
	}
	
	public static void setes() {
		
		HashSet<Person> set = new HashSet<>();
		Person person1 = new Person(1, "Vezetek1", "Kereszt1");
		Person person2 = new Person(1, "Vezetek1", "Kereszt1");
		Person person3 = new Person(2, "Vezetek1", "Kereszt1");
		set.add(person1);
		set.add(person2);
		set.add(person3);
		
		//Mivel a hashCode �s equals fel�l lett �rva, �gy a m�ret 2
		System.out.println("A m�ret: " + set.size());
		System.out.println("Az elemek------");
		set.forEach(person -> System.out.println(person));
		
	}
	
	public static void mapes() {
		HashMap<Integer, Person> map = new HashMap<>();
		Person person1 = new Person(1, "Vezetek1", "Kereszt1");
		Person person2 = new Person(2, "Vezetek2", "Kereszt2");
		Person person3 = new Person(3, "Vezetek3", "Kereszt3");
		map.put(1, person1);
		map.put(2, person2);
		map.put(3, person3);
		
		//1. ciklus
		Iterator<Map.Entry<Integer, Person>> it = map.entrySet().iterator();
		
		while(it.hasNext()) {
			//Ezt musz�j kimenteni, lentebb it.next().getKey() �s getValue() nem m�k�dne
			Map.Entry<Integer, Person> entry = it.next();
			
			//Kimenthet�ek
			int id = entry.getKey();
			Person person = entry.getValue();
			
			System.out.println("Az id: " + entry.getKey());
			System.out.println("A person: " + entry.getValue());
		}
		
		//2. ciklus
		System.out.println("2. ciklus---------------------");
		for(Map.Entry<Integer, Person> entry: map.entrySet()) {
			int id = entry.getKey();
			Person person = entry.getValue();
			System.out.println("Az id: " + id);
			System.out.println("A person: " + person);
		}
		
		
		//3. ciklus
		System.out.println("3. ciklus---------------------");
		map.forEach((key, value) -> {
			int id = key;
			Person person = value;
			System.out.println("Az id: " + id);
			System.out.println("A person: " + person);
		});
		
		
		if(map.containsKey(2)) {
			System.out.println("Tartalmazza a key-t, a hozz�tartoz� �rt�k: " + map.get(2).getFirstName());
		}else {
			System.out.println("Nem tartalmazza a key-t");
		}
		
		
		//HashCode �s equals n�lk�l is m�k�dik
		if(map.containsValue(person3)) {
			System.out.println("Tartalmazza a value-t");
		}else {
			System.out.println("Nem tartalmazza a value-t");
		}
		
		//Csak ha m�g nincs ez a key
		map.putIfAbsent(4, person2);
		System.out.println("Hozz�ad�sn�l putifabsent: " + map.get(4));
		
		//Ha m�r van a key, fel�l�rja
		map.put(4, person1);
		System.out.println("Hozz�ad�sn�l sima put: " + map.get(4));
		
//		map.remove(4, person3); //Mindenk�ppen t�rli ezt a key-t
		
		//Csak akkor t�rli, ha ehhez a keyhez ez a value tartozik
		map.remove(4, person3);
		System.out.println("M�ret: " + map.size());
		
	}
}
