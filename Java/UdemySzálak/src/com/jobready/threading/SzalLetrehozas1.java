package com.jobready.threading;

public class SzalLetrehozas1 {

	public static void main(String[] args) {
		
		System.out.println("Starting thread 1 (extendes)");
		Task taskRunner = new Task("Thread-a"); //Alapból nem kéne a paraméter, de mi csináltunk egy ilyen konstruktort is
		taskRunner.start();
		
		System.out.println("Starting thread 2 (extendes)");
		Task taskRunner2 = new Task("Thread-b");
		taskRunner2.start();
		
		System.out.println("Starting thread 3 (interfaces)");
		Task2 interfacesOsztaly = new Task2();
		Thread t1 = new Thread(interfacesOsztaly);
		t1.start();
		
		System.out.println("Starting thread 4 (interfaces)");
		Thread t2 = new Thread(new Task2());
		t2.start();
		
		
		System.out.println("Starting thread 5 (interfaces anonim)");
		Thread t3 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("number: " + i +" " + Thread.currentThread().getName()); //Hogy megtudjuk milyen nevû thread írja ki éppen (ez visszaadja a nevét, amit mi felülírtunk, de lehetett volna a default is)
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		t3.start();
	}

}


class Task extends Thread {
	
	public String name;
	
	public Task() {
		
	}

	
	public Task(String name) {
		this.name = name;
	}



	public void run() {
		//Thread.currentThread().setName(this.name);
		for (int i = 0; i < 1000; i++) {
			System.out.println("number: " + i +" " + Thread.currentThread().getName()); //Hogy megtudjuk milyen nevû thread írja ki éppen (ez visszaadja a nevét, amit mi felülírtunk, de lehetett volna a default is)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}



class Task2 implements Runnable {
	
	public String name;
	
	public Task2() {
		
	}

	
	public Task2(String name) {
		this.name = name;
	}



	public void run() {
		//Thread.currentThread().setName(this.name);
		for (int i = 0; i < 1000; i++) {
			System.out.println("number: " + i +" " + Thread.currentThread().getName()); //Hogy megtudjuk milyen nevû thread írja ki éppen (ez visszaadja a nevét, amit mi felülírtunk, de lehetett volna a default is)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
