package com.byhieg.springbootintegrationlock.thread.asynctosync;

import java.util.concurrent.*;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 * 
 * 这种是我们自己实现一个异步接口，并且吧结果通过futureTask的方式透出。
 * 这样调用者可以使用futureTask来处理结果。
 */
public class FutureTaskExample {
	
	static class Api{
		private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
		
		private FutureTask<String> submit(){
			FutureTask<String> futureTask = new FutureTask<>(() -> {
				Thread.sleep(1000);
				return "success";
			});
			threadPoolExecutor.submit(futureTask);
			return futureTask;
		}

		public String fetchList() throws ExecutionException, InterruptedException {
			return submit().get();
		}

		public void close(){
			threadPoolExecutor.shutdown();
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Api api = new Api();
		System.out.println(api.fetchList());
		api.close();
	}
}
