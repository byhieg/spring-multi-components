package com.byhieg.springbootintegrationlock.thread.masterworker;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 */
public class Master {

	private BlockingQueue<Integer> taskQueue;

	public BlockingQueue<Result> getResultQueue() {
		return resultQueue;
	}

	private BlockingQueue<Result> resultQueue;
	private List<Worker> workerList;
	private volatile boolean running;

	private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

	public Master() {
		taskQueue = new ArrayBlockingQueue<>(10);
		resultQueue = new LinkedBlockingDeque<>(10);
		workerList = new ArrayList<>();
		running = false;
	}

	public void registerWorker(Worker worker) {
		workerList.add(worker);
	}

	public void start() throws InterruptedException {
		running = true;
		handleRes();
		while (running) {
			Integer task = taskQueue.take();
			int workerNum = task % workerList.size();
			Worker worker = workerList.get(workerNum);
			worker.handle(task);
		}
		System.out.println("master dispatch worker shutdown");
	}

	public void handleRes() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					try {
						Result result = resultQueue.take();
						threadPoolExecutor.submit(new Runnable() {
							@Override
							public void run() {
								System.out.printf("worker-%s:task:[%s] result:[%s]%n", result.getWorkerNum(), result.getTaskValue(), result.getResultValue());
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				System.out.println("master handle worker res shutdown");
			}
		}).start();
	}

	public void submitTask(Integer task) throws InterruptedException {
		taskQueue.put(task);
	}

	public void shutdown() {
		running = false;
		for (Worker worker : workerList) {
			worker.shutdown();
		}
		threadPoolExecutor.shutdown();
	}


}
