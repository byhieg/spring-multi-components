package com.byhieg.springbootintegrationlock.thread.masterworker;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 */
public class Result {
	
	public int workerNum;
	public int taskValue;
	public int resultValue;

	public Result(int workerNum, int taskValue, int resultValue) {
		this.workerNum = workerNum;
		this.taskValue = taskValue;
		this.resultValue = resultValue;
	}

	public int getWorkerNum() {
		return workerNum;
	}

	public int getTaskValue() {
		return taskValue;
	}

	public int getResultValue() {
		return resultValue;
	}
}
