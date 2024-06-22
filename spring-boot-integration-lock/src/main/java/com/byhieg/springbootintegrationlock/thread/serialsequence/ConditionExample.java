package com.byhieg.springbootintegrationlock.thread.serialsequence;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 * 
 * 使用 condition 来交替打印1-100 
 * t1 打印奇数 
 * t2 打印偶数
 * condition 条件为 是否当前数字是奇数还是偶数。
 * 对于奇数线程而言，如果是偶数，则让出线程。等待别的线程通知。
 * 对于偶数线程而言，如果是奇数，则让出线程，等待别的线程通知。
 * 如果满足了条件，则执行打印，并且将数字 + 1，通知对应的奇数或者偶数线程往下走。
 */
public class ConditionExample {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(atomicInteger.get() <= 100){
					try{
						lock.lock();
						while (atomicInteger.get() % 2 == 0 && atomicInteger.get() <= 100){
							condition.await();
						}
						if(atomicInteger.get() > 100){
							break;
						}
						System.out.println("thread-odd:   " + atomicInteger.get());
						atomicInteger.getAndIncrement();
						condition.signal();
					}catch (InterruptedException e){
						e.printStackTrace();
					}finally {
						lock.unlock();
					}
				}
				countDownLatch.countDown();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(atomicInteger.get() <= 100){
					try{
						lock.lock();
						while (atomicInteger.get() % 2 == 1 && atomicInteger.get() <= 100) {
							condition.await();
						}
						if(atomicInteger.get() > 100){
							condition.signal();
							break;
						}
						System.out.println("thread-even:  " + atomicInteger.get());
						atomicInteger.getAndIncrement();
						condition.signal();
					}catch (InterruptedException e){
						e.printStackTrace();
					}finally {
						lock.unlock();
					}
				}
				countDownLatch.countDown();
			}
		});
		
		t1.start();
		t2.start();
		countDownLatch.await();
	}
}
