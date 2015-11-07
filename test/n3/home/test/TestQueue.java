package n3.home.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.util.Assert;

import n3.home.kernel.CircularDoubleBufferedQueue;

public class TestQueue {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public void main(String[] args,String str) throws Exception {
		final Queue<String> queue = new CircularDoubleBufferedQueue<String> (10);
		Runnable in = new Runnable() {
			@Override
			public void run() {
				int m = new Random().nextInt(20);
//				queue.add(String.valueOf(m));
			}
		};
		Runnable out = new Runnable() {
			@Override
			public void run() {
//				System.out.println("output - "+list.poll());
//				for(int m=0; m<100; m++) {
//				}
			}
		};
		for(int m=0; m<100; m++) {
			new Thread(in).start();
//			new Thread(out).start();
		}
		Thread.sleep(5000);
		for(int m=0; m<100; m++) {
			Assert.notNull(queue.poll());
		}
	}

	public static void main(String[] args) throws Exception {
		LinkedBlockingQueue<Integer> q = new LinkedBlockingQueue<Integer>();
		q.put(1);
		q.put(2);
		q.take();
	}
	static class MyThread implements Runnable{    
		  
		  private static final Lock lock = new ReentrantLock();    
		  public void run(){    
		      try{    
//		    	  lock.lock();   
		    	  lock.lockInterruptibly();     
//		    	  lock.tryLock();
		          System.out.println(Thread.currentThread().getName() + " starting");    
		          TimeUnit.SECONDS.sleep(5);
		          lock.unlock();
		          System.out.println(Thread.currentThread().getName() + " finished");    
		      }    
		      catch (Exception e){    
		          System.out.println(Thread.currentThread().getName() + " interrupted");
//		          e.printStackTrace();
		      } 
		  
		  }
	}
}
