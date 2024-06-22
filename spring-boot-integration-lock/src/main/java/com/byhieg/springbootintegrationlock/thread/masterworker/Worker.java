package com.byhieg.springbootintegrationlock.thread.masterworker;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 */
public class Worker {

	private BlockingQueue<Result> resultQueue;

	private ExecutorService executorService;

	private int num;

	public Worker(int num, BlockingQueue<Result> resultQueue) {
		this.resultQueue = resultQueue;
		executorService = Executors.newSingleThreadExecutor();
		this.num = num;
	}

	public void handle(Integer task) {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("worker-" + num + " handle task");
				try {
					if (num == 1) {
						Thread.sleep(10);
					}
					resultQueue.put(new Result(num, task, task * task));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void shutdown() {
		executorService.shutdown();
	}


}
