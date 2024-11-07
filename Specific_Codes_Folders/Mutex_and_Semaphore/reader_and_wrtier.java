package Mutex_and_Semaphore;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class reader_and_wrtier{
	static Semaphore mutex = new Semaphore(1);
	static Semaphore wrt = new Semaphore(1);
	static int readCount = 0;
	static String message = "Hello World";
	Scanner sc = new Scanner(System.in);
	static class Reader implements Runnable {

		@Override
		//Reader Function
		public void run() {
			try {
				mutex.acquire();
				readCount++;
				if(readCount == 1 ) {
				wrt.acquire();
				}
				mutex.release();
				System.out.println("Thread " + Thread.currentThread().getName() + " is Reading : " + message);
				Thread.sleep(2500);
				System.out.println("Thread " + Thread.currentThread().getName() + " has Finished Reading. ");
				mutex.acquire();
				readCount --;
				if(readCount == 0) {
					wrt.release();
				}
				mutex.release();
			}
			catch (InterruptedException e ) {
				System.out.println(e.getMessage());
			}
		}	
	}
	static class Writer implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				wrt.acquire();
				message = "The World is Beautiful";
				System.out.println("Thread " + Thread.currentThread().getName()+ " is Writing : " + message);
				
				Thread.sleep(2500);
				System.out.println("Thread " + Thread.currentThread().getName() + " has finished Writing");
				wrt.release();
			}
			catch(InterruptedException e ) {
				System.out.println(e.getMessage());
			}
		}
	}
	public static void main(String[] args) {
		Reader read = new Reader();
		Writer write = new Writer();
		Thread r1 = new Thread(read);
		r1.setName("Reader1");
		Thread r2 = new Thread(read);
		r2.setName("Reader2");
		Thread r3 = new Thread(read);
		r3.setName("Reader3");
		Thread w1 = new Thread(write);
		w1.setName("Writer1");
		Thread w2 = new Thread(write);
		w2.setName("Writer2");
		Thread w3 = new Thread(write);
		w3.setName("Writer3");

		w1.start();
		r1.start();
		w2.start();
		r2.start();
		w3.start();
		r3.start();
	}
}

