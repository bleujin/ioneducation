package net.ion.bleujin.multithread.readswrite.modify;

public final class ReadWriteLock {
	private int readingReaders = 0; // (A)
	private int waitingWriters = 0; // (B)
	private int writingWriters = 0; // (C)
	private boolean preferWriter = true; 

	public synchronized void readLock() throws InterruptedException {
		while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
			this.wait();
		}
		readingReaders++; // (A)
	}

	public synchronized void readUnlock() {
		readingReaders--; // (A)
		preferWriter = true;
		notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException {
		waitingWriters++; // (B)
		try {
			while (readingReaders > 0 || writingWriters > 0) {
				wait();
			}
		} finally {
			waitingWriters--; // (B)
		}
		writingWriters++; // (C)
	}

	public synchronized void writeUnlock() {
		writingWriters--; // (C)
		preferWriter = false;
		this.notifyAll();
	}
}
