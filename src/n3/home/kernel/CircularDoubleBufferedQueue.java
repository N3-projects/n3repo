package n3.home.kernel;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CircularDoubleBufferedQueue<E> extends AbstractQueue<E> /*implements BlockingQueue<E>*/ {

	private final ReentrantLock readLock;
	private final ReentrantLock writeLock;
	private final int size;
	private final AtomicInteger readCount = new AtomicInteger(0);
	private final AtomicInteger writeCount = new AtomicInteger(0);
	private Node<E> readHead;
	private Node<E> writeHead;
	private Node<E> readTail;
	private Node<E> writeTail;
	
	public CircularDoubleBufferedQueue(int capacity) {
		size = capacity;
		readLock = new ReentrantLock(); 
		writeLock = new ReentrantLock();
	}
	
	@Override
	public int size() {
		readLock.lock();
		try {
            return readCount.get();
        } finally {
        	readLock.unlock();
        }
	}

	@Override
	public E poll() {
		readLock.lock();
		try{
			if(readHead==null) {
				return null;
			}
			E e = null;
			if(readCount.get()==1 && writeLock.tryLock()) {
				e = dequeue();
				swithLink();
			}
			return e;
		} finally {
			readLock.unlock();
		}
	}

	private void swithLink() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public E peek() {
		readLock.lock();
		try{
			return readHead==null ? null : readHead.item;
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public boolean offer(E e) {
		if(e == null) {
			throw new NullPointerException("the element can not be null");
		}
		writeLock.lock();
		try {
			if(writeCount.get()==size) {
				return false;
			}
			Node<E> n = new Node<E> (e);
			if(writeTail==null) {
				writeHead = n;
				writeTail = n;
			} else {
				writeTail.next = n;
				writeTail = n;
			}
			writeCount.getAndIncrement();
			return true;
		} finally {
			writeLock.unlock();
		}
	}
/*
	@Override
	public void put(E e) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E take() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remainingCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private E dequeue() {
		Node<E> h = readHead;
		E e = h.item;
		readHead = readHead.next;
		h.next = null;
		return e;
	}

	private static class Node<E> {
		
		public Node() {
		}
		public Node(E item) {
			this.item = item;
			this.next = null;
		}
		private E item;
		private Node<E> next;
	}
}
