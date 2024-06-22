package com.byhieg.springbootintegrationlock.thread.synctool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 * 
 * 使用lock condition,实现CountDown latch
 */
public class MyCountDownLatch {

	private AtomicInteger counter;
	private Lock lock;
	private Condition condition;

	public MyCountDownLatch(int count) {
		counter = new AtomicInteger(count);
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}

	public void countDown() {
		counter.decrementAndGet();
		if (counter.get() == 0){
			try{
				lock.lock();
				condition.signalAll();
			}finally {
				lock.unlock();
			}
		}
	}

	public int getCount() {
		return counter.get();
	}

	public void await() throws InterruptedException {
		try {
			lock.lock();
			while (counter.get() > 0) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}

	public void await(long timeout, TimeUnit unit) throws InterruptedException {
		try {
			lock.lock();
			while (counter.get() > 0) {
				condition.await(timeout, unit);
			}
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MyCountDownLatch countDownLatch = new MyCountDownLatch(2);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println("thread 1 finish");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				countDownLatch.countDown();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("thread 2 finish");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				countDownLatch.countDown();
			}
		});
		t1.start();
		t2.start();
		countDownLatch.await();
		System.out.println("wait thread1 2 success");
	}
}
