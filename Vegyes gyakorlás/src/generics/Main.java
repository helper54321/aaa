package generics;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		GenericEmployee<String> emp1 = new GenericEmployee<>("John");
		GenericEmployee<Integer> emp2 = new GenericEmployee<>(25);
		
		GenericAccountant<String, Integer> accountant1 = new GenericAccountant<String, Integer>("Mary", 37);
		
		GenericEmployee<String> mixed1 = new GenericAccountant<String, Double>("Mixed", 62.3);
		
		System.out.println("Stringes employee: " + emp1.getVal() + ", típusa string-e: " + (emp1.getVal() instanceof String));
		System.out.println("Intes employee: " + emp1.getVal() + ", típusa int-e: " + (emp2.getVal() instanceof Integer));
		
		System.out.println("String és intes accountant: " + accountant1.getVal1() + ", típusa string-e: " + (accountant1.getVal1() instanceof String) + ", " + accountant1.getVal2() + ", típusa int-e: " + (accountant1.getVal2() instanceof Integer));
		
		System.out.println("Kevert employee: " + mixed1.getVal() + ", típusa string-e: " + (mixed1.getVal() instanceof String));
		
		
		
		System.out.println();
		System.out.println("Meghívni a genericFunctiont ----------------");
		
		for(Object o : genericFunction(emp1, emp2)) {
			System.out.println(o);
		}
		
		
		System.out.println();
		System.out.println("Upper and lower bound ----------------------");
		
		//Bármilyen típus lehetne
		ArrayList<?> list1 = new ArrayList<>();
		list1 = new ArrayList<PlainAccountant>(); 
		
		//PlainEmployee vagy bármi ami azt kiterjeszti
		ArrayList<? extends PlainEmployee> list2 = new ArrayList<>();
		list2 = new ArrayList<PlainAccountant>(); 
		
		//PlainAccountant vagy bármi ami annak parentje
		ArrayList<? super PlainAccountant> list3 = new ArrayList<>();
		list3 = new ArrayList<PlainEmployee>();
		
		
	}
	
	
	public static <E, F> ArrayList<Object> genericFunction(E o1, F o2) {
		ArrayList<Object> list = new ArrayList<>();
		list.add(o1);
		list.add(o2);
		
		return list;
	}


}
