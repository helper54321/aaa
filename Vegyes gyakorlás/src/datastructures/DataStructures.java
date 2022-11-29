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
		
		System.out.println("Tartalmazza: " + arrayList.contains(person1)); //hashcode és equals felülírás nélkül is true
		
		System.out.println("Foreach elõtt---------------");
		arrayList.forEach(person -> System.out.println(person));
		
		System.out.println("Iterator elõtt--------------");
		Iterator<Person> it = arrayList.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		
		arrayList.removeIf(Person.myPredicate());
		
		System.out.println("RemoveIf után");
		arrayList.forEach(person -> System.out.println(person));
		
		Object[] arr = arrayList.toArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.println("Tömb formában: " + (arr[i] instanceof Person));
		}

		
		arrayList.add(person2);
		
		//utolsó elem törlése
		arrayList.remove(arrayList.size() - 1);
		System.out.println("Utolsó elem törlése után");
		
		arrayList.forEach(person -> System.out.println(person));
		
		
		//mind2 lehetséges
		ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
		
		//hozzátarotozó ciklus
		for(ArrayList<Integer> item : list2) {
			for(int i : item) {
				
			}
		}
		
		ArrayList<Integer[]> list3 = new ArrayList<Integer[]>();
		
		//hozzátarotozó ciklus
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
		
		//A legelsõként hozzáadott elemet törli
		linkedList.pop();
		
		//utolsó elem törlése után
		System.out.println("Utolsó elem törlése után");
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
		
		//Mivel a hashCode és equals felül lett írva, így a méret 2
		System.out.println("A méret: " + set.size());
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
			//Ezt muszáj kimenteni, lentebb it.next().getKey() és getValue() nem mûködne
			Map.Entry<Integer, Person> entry = it.next();
			
			//Kimenthetõek
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
			System.out.println("Tartalmazza a key-t, a hozzátartozó érték: " + map.get(2).getFirstName());
		}else {
			System.out.println("Nem tartalmazza a key-t");
		}
		
		
		//HashCode és equals nélkül is mûködik
		if(map.containsValue(person3)) {
			System.out.println("Tartalmazza a value-t");
		}else {
			System.out.println("Nem tartalmazza a value-t");
		}
		
		//Csak ha még nincs ez a key
		map.putIfAbsent(4, person2);
		System.out.println("Hozzáadásnál putifabsent: " + map.get(4));
		
		//Ha már van a key, felülírja
		map.put(4, person1);
		System.out.println("Hozzáadásnál sima put: " + map.get(4));
		
//		map.remove(4, person3); //Mindenképpen törli ezt a key-t
		
		//Csak akkor törli, ha ehhez a keyhez ez a value tartozik
		map.remove(4, person3);
		System.out.println("Méret: " + map.size());
		
	}
}
