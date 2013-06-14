package net.ion.bleujin.multithread.readswrite ;

import net.ion.bleujin.multithread.readswrite.modify.Main;

public class ReaderThread extends Thread {
    private final Data data;
    public ReaderThread(Data data) {
        this.data = data;
    }
    public void run() {
        try {
            long begin = System.currentTimeMillis();
            for (int i = 0; i < Main.LOOP_COUNT; i++) {
                char[] readbuf = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readbuf));
            }
            long time = System.currentTimeMillis() - begin;
            System.out.println(Thread.currentThread().getName() + ": time = " + time);
        } catch (InterruptedException e) {
        }
    }
}
