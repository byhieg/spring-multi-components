package com.byhieg.springbootintegrationlock.thread.asynctosync;

import java.util.concurrent.*;

/**
 * Created by byhieg on 2024/6/22.
 * Mail to byhieg@gmail.com
 * 
 * 使用countDown latch 实现异步转同步
 * 一般是第三方接口，提供一个callback。我们需要等这个接口异步走完。然后继续处理。
 * 我们可以在callbck中，使用count down来异步转同步。
 */
public class CountdownExample {


	static class Api {

		private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

		public Api() {
		}

		interface Callback {

			void onSuccess(String result);

			void onFailed(Exception exception);
		}
		public void submitWithCallback(Callback callback){
			threadPoolExecutor.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						callback.onFailed(e);
					}
					callback.onSuccess("success");
				}
			});
		}
		
		class Data {
			
			private String result;

			public String getResult() {
				return result;
			}

			public void setResult(String result) {
				this.result = result;
			}
		}

		public String fetchList()  {
			CountDownLatch countDownLatch = new CountDownLatch(1);
			Data data = new Data();
			submitWithCallback(new Callback() {
				@Override
				public void onSuccess(String result) {
					data.setResult(result);
					countDownLatch.countDown();
				}

				@Override
				public void onFailed(Exception exception) {
					data.setResult(exception.getMessage());
					countDownLatch.countDown();
				}
			});
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return data.getResult();
		}
		
		public void close(){
			threadPoolExecutor.shutdown();
		}
	}

	public static void main(String[] args) {
		Api api = new Api();
		System.out.println(api.fetchList());
		api.close();
	}
}
