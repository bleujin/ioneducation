package net.ion.bleujin.multithread.start;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Thread t = new MyThread() ;
		t.start() ;

		t.join() ;

	}
}


class MyThread extends Thread {
	
	public void run(){
		System.out.println("Start") ;
		for (int i = 0; i < 100; i++) {
			System.out.println("Hello " + i) ;
		}
		System.out.println("End") ;
	}
} 