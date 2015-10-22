package net.ion.bleujin.multithread.sgate;

import junit.framework.TestCase;

public class TestSecGate extends TestCase {
	
	public void testRun() throws Exception{
		System.out.println("Testing SecurityGate...");
		for (int trial = 0; true; trial++) {
			SecurityGate gate = new SecurityGate();
			UserThread[] t = new UserThread[50];

			// CrackerThread
			for (int i = 0; i < t.length; i++) {
				t[i] = new UserThread(gate);
				t[i].start();
			}

			for (int i = 0; i < t.length; i++) {
				t[i].join();
			}

			if (gate.getCounter() == 0) {
				System.out.print(".");
			} else {
				System.out.println("SecurityGate is NOT safe!");
				System.out.println("getCounter() == " + gate.getCounter());
				System.out.println("trial = " + trial);
				break;
			}
		}
	}
}

class UserThread extends Thread {
    private final SecurityGate gate;
    public UserThread(SecurityGate gate) {
        this.gate = gate;
    }
    public void run() {
        for (int i = 0; i < 10000; i++) {
            gate.enter();
            gate.exit();
        }
    }
}


class SecurityGate {
    private int counter = 0;
    public  void enter() {
    	counter++ ;

    }
    public  void exit() {
    	counter-- ;
    }
    public int getCounter() {
        return counter;
    }
}
