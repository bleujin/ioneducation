package net.ion.bleujin.multithread.gate;

import net.ion.framework.util.InfinityThread;
import junit.framework.TestCase;

public class TestGate extends TestCase{
	
	public void testGate(){
		System.out.println("Testing Gate, hit CTRL+C to exit.");
		Gate gate = new Gate();
		new UserThread(gate, "Alice", "Alaska").start();
		new UserThread(gate, "Bobby", "Brazil").start();
		new UserThread(gate, "Chris", "Canada").start();
		
		new InfinityThread().startNJoin() ;
	}
	
	
}

class UserThread extends Thread {
	private final Gate gate;
	private final String myname;
	private final String myaddress;

	public UserThread(Gate gate, String myname, String myaddress) {
		this.gate = gate;
		this.myname = myname;
		this.myaddress = myaddress;
	}

	public void run() {
		System.out.println(myname + " BEGIN");
		while (true) {
			gate.pass(myname, myaddress);
		}
	}
}

class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";
    public void pass(String name, String address) {
        this.counter++;
        this.name = name;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        this.address = address;
        check();
    }
    public String toString() {
        return "No." + counter + ": " + name + ", " + address;
    }
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("***** BROKEN ***** " + toString());
        }
    }
}


/**
 * 등장인물
 *   - Shared Resource
 *     safeMethod
 *     unsafeMethod
 * 
 * 어떨때에
 *   - 멀티 쓰레드
 *   - 복수의 쓰레드가 접근할때
 *   - 상태가 변화할 가능성이 있을때.
 *   - 안전성을 가질 필요가 있을때
 *   
 * 
 * 상속과 멀티 쓰레드
 *   - 상속에 의해 파괴될수 있다. 
 * 
 * Cretical Section
 *    - 락의 취득에 시간이 걸린다. 
 *    - 쓰레드의 conflict에 의해 기다린다. 
 *    
 */
