package net.ion.bleujin.multithread.readswrite.modify ;

public class Main {
    public static final int LOOP_COUNT = 100;

	public static void main(String[] args) {
        Data data = new Data(10);
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new WriterThread(data, "ABCDEFGHIJKLMNOPQTSTUVWXYZ").start();
        new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
    }
}



/**
 * Read-Write Lock의 등장인물
 *   - Reader
 *   - Writer
 *   - Shared Resource
 *   - ReadWrite Lock
 *   
 *   
 * Thinking Hint
 *   - 읽기 처리가 중요할때에 유효
 *   - 읽기 빈도가 쓰는 빈도보다도 높을때의 유효
 *   - Lock 의미
 */
