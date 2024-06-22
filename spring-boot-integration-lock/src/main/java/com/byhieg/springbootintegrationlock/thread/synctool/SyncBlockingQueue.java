package com.byhieg.springbootintegrationlock.thread.synctool;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 * 
 * 使用lock condition 实现同步阻塞队列。
 * 当队列满时，使用notFull.await。这样，当队列移除元素时，notFull 将会signal，从而继续添加元素。
 * 当队列空时， 使用notEmpty.await，这样，当队列添加第一元素时，notEmpty 将会signal.从而从队列移除元素。
 */
public class SyncBlockingQueue<T> {

	private List<T> queue;

	private int capacity;

	private Lock lock;
	private Condition notEmpty;
	private Condition notFull;

	public SyncBlockingQueue(int capacity) {
		this.capacity = capacity;
		queue = new ArrayList<>(capacity);
		lock = new ReentrantLock();
		notEmpty = lock.newCondition();
		notFull = lock.newCondition();
	}

	public void put(T t) throws InterruptedException {
		try {
			lock.lock();
			while (queue.size() == capacity) {
				notFull.await();
			}
			queue.add(t);
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}


	public T take() throws InterruptedException {
		try {
			lock.lock();
			while (queue.isEmpty()) {
				notEmpty.await();
			}
			T t = queue.remove(0);
			notFull.signalAll();
			return t;
		} finally {
			lock.unlock();
		}
	}

	public T peek() throws InterruptedException {
		return queue.isEmpty() ? null : queue.get(0);
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public int getSize() {
		return queue.size();
	}

	public static void main(String[] args) throws InterruptedException {
		List<Integer> tasks = Arrays.asList(1,2,3,4,5);
		SyncBlockingQueue<Integer> blockingQueue = new SyncBlockingQueue<>(1);
		CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (Integer task : tasks) {
					try {
						blockingQueue.put(task);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				countDownLatch.countDown();
			}
		}).start();
	
		
		Thread.sleep(100);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!blockingQueue.isEmpty()) {
					try {
						System.out.println(blockingQueue.take());
						Thread.sleep(10);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				countDownLatch.countDown();
			}
		}).start();
		countDownLatch.await();
	}
}
