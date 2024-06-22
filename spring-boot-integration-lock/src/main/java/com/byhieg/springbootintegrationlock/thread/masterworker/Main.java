package com.byhieg.springbootintegrationlock.thread.masterworker;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Master master = new Master();
		for (int i = 0; i < 2; i++) {
			master.registerWorker(new Worker(i, master.getResultQueue()));
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0 ; i < 20;i++){
					try {
						System.out.println("master 提交任务:" + i);
						Thread.sleep(200);
						master.submitTask(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		master.start();
	}
}
