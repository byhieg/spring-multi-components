package com.byhieg.springbootintegrationlock.thread.serialsequence;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 * 使用信号量 来实现交替打印1-100
 * 使用两个信号量，奇数信号量 和偶数信号量
 * 奇数线程 当值为奇数时，申请奇数信号量进行打印，如果申请不到，则等待
 * 偶数线程，当值为偶数时，申请偶数信号量进行打印，如果申请不到，则等待
 * 这样，当奇数线程满足条件时，执行逻辑，然后释放偶数的信号量。这样偶数线程则会执行
 * 当偶数线程满足提交时，执行逻辑，然后释放奇数的信号量，这样奇数线程会执行。
 */
public class SemaphoreExample {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		AtomicInteger atomicInteger = new AtomicInteger(1);
		Semaphore oddSemaphore = new Semaphore(1);
		Semaphore evenSemaphore = new Semaphore(0);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (atomicInteger.get() <= 100) {
					try {
						if (atomicInteger.get() % 2 == 1){
							oddSemaphore.acquire();
							System.out.println("thread-odd:  " + atomicInteger.get());
							atomicInteger.getAndIncrement();
							evenSemaphore.release();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				countDownLatch.countDown();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (atomicInteger.get() <= 100) {
					try {
						if (atomicInteger.get() % 2 == 0){
							evenSemaphore.acquire();
							System.out.println("thread-even: " + atomicInteger.get());
							atomicInteger.getAndIncrement();
							oddSemaphore.release();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
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
