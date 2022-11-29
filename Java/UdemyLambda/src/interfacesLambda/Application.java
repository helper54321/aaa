package interfacesLambda;

public class Application {

	public static void main(String[] args) {
		
		Human tom = new Human();
		tom.walk();
		
		walker(tom);
		
		Robot wale = new Robot();
		wale.walk();
		
		walker(wale);
		
		walker(new Walkable() {

			@Override
			public void walk() {
				System.out.println("Custom object walking");
				
			}
			
		});
		
		
		walker(() -> System.out.println("AA object walking"));
		
		//El is menthetjük értékként (nyílván ebben a példában nem sok értelme van)
		//Annyi a feltétele, hogy funkcionális interface-nek kell lennie amibe elmentjük (csak 1 absztrakt metódusa van)
		//És nyílván annál a funkcionális interface-nél a metódus paraméternek egyeznie kell ezzel
		ALambdaInterface aBlockOfCode = () -> System.out.println("AA object walking");
		aBlockOfCode.something();
		
		//Attól, fogy egy funkcionális interface-t adok át, a típusa még nem fog megegyezni, így természetesen ezt nem fogadja el
		//walker(aBlockOfCode);
		
		Walkable aBlockOfCode2 = () -> System.out.println("AA object walking");
		walker(aBlockOfCode2); //Itt már a típus is oké, így elfogadja
		
		ALambdaInterface helloVar = () -> System.out.println("Hello there");
		
		Calculate sumVar = (a, b) -> a + b;
		System.out.println(sumVar.compute(4, 6));
		
		//Ilyenkor már ki kell írni a return-t, mert anélkül nem egyértelmû
		Calculate nonZeroDivider = (a, b) -> {
			if(a == 0) {
				return 0;
			}
			return a / b;
		};
		
		System.out.println(nonZeroDivider.compute(42, 8));
		
	   StringWorker reverser = str -> {
			String result = "";
			for (int i = str.length() - 1; i >= 0; i--) {
				result += str.charAt(i);
			}
			
			return result;
		};
		
		//Generikus interface-val
		MyGenericInterface<String> reverser2 = str -> {
			String result = "";
			for (int i = str.length() - 1; i >= 0; i--) {
				result += str.charAt(i);
			}
			
			return result;
		};
		
		System.out.println(reverser.work("alma"));
		System.out.println(reverser2.work("körte"));
		
		Fact f = num -> {
			int result = 1;
			for(int i = 1; i <= num; i++) {
				result *= i;
			}
			
			return result;
		};
		
		//Generikus interface-val
		MyGenericInterface<Integer> f2 = num -> {
			int result = 1;
			for(int i = 1; i <= num; i++) {
				result *= i;
			}
			
			return result;
		};
		
		System.out.println(f.factorial(5));
		System.out.println(f2.work(10));
		
		

		
		
	}
	
	public static void walker(Walkable walkableEntity) {
		walkableEntity.walk();
	}
	
	public void sayHello() {
		System.out.println("Hello there");
	}
	
	public int sum(int arg1, int arg2) {
		return arg1 + arg2;
	}
	
	public int nonZeroDivide(int a, int b) {
		if(a == 0) {
			return 0;
		}
		return a / b;
	}
	
	public String reverse(String str) {
		String result = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			result += str.charAt(i);
		}
		
		return result;
	}
	
	public int factorial(int num) {
		int result = 1;
		for(int i = 1; i <= num; i++) {
			result *= i;
		}
		
		return result;
	}
	
	
	interface Calculate{
		public int compute(int a, int b);
	}
	
	interface StringWorker{
		public String work(String s);
	}
	
	interface Fact{
		public int factorial(int num);
	}
	
	interface MyGenericInterface<T>{
		public T work(T s);
	}
}
