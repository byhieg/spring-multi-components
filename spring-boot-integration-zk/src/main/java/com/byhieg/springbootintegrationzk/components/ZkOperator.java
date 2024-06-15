package com.byhieg.springbootintegrationzk.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.stereotype.Component;

/**
 * Created by byhieg on 2024/6/12.
 * Mail to byhieg@gmail.com
 */
@Component
@Slf4j
public class ZkOperator {

	private final ZkProperties zkProperties;
	@Getter
	private CuratorFramework client = null;
	private CuratorCache cache = null;

	public ZkOperator(ZkProperties zkProperties) {
		this.zkProperties = zkProperties;
	}

	@PostConstruct
	public void init() {
		/**
		 * 同步创建zk示例，原生api是异步的
		 *
		 * curator链接zookeeper的策略:ExponentialBackoffRetry
		 * baseSleepTimeMs：初始sleep的时间
		 * maxRetries：最大重试次数
		 * maxSleepMs：最大重试时间
		 */
//		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);

		/**
		 * curator链接zookeeper的策略:RetryNTimes
		 * n：重试的次数
		 * sleepMsBetweenRetries：每次重试间隔的时间
		 */
		RetryPolicy retryPolicy = new RetryNTimes(zkProperties.getMaxRetries(), zkProperties.getMaxSleepTime());

		/**
		 * curator链接zookeeper的策略:RetryOneTime
		 * sleepMsBetweenRetry:每次重试间隔的时间
		 */
//		RetryPolicy retryPolicy2 = new RetryOneTime(3000);

		/**
		 * 永远重试，不推荐使用
		 */
//		RetryPolicy retryPolicy3 = new RetryForever(retryIntervalMs)

		/**
		 * curator链接zookeeper的策略:RetryUntilElapsed
		 * maxElapsedTimeMs:最大重试时间
		 * sleepMsBetweenRetries:每次重试间隔
		 * 重试时间超过maxElapsedTimeMs后，就不再重试
		 */
//		RetryPolicy retryPolicy4 = new RetryUntilElapsed(2000, 3000);

		client = CuratorFrameworkFactory.builder()
				.connectString(zkProperties.getZkServers())
				.connectionTimeoutMs(zkProperties.getConnectionTimeout())
				.sessionTimeoutMs(zkProperties.getSessionTimeout()).retryPolicy(retryPolicy)
				.namespace(zkProperties.getNamespace()).build();
		client.start();
		// watch node
		CuratorCache cache = CuratorCache.builder(this.client, "/").build();
		CuratorCacheListener listener = CuratorCacheListener.builder()
				.forInitialized(() -> {
					System.out.println("Initialized!");
				})
				.forCreates(childData -> log.info("Creates! {}", childData))
				.forChanges((childData, childData1) -> log.info("Changes! {}, {}", childData, childData1))
				.forCreatesAndChanges((childData, childData1) -> log.info("CreatesAndChanges! {}, {}", childData, childData1))
				.forDeletes(childData -> log.info("Deletes! {}", childData))
				.forAll((type, childData, childData1) -> log.info("All! {}, {}, {}", type, childData, childData1))
				.build();
		cache.listenable().addListener(listener);
		cache.start();
	}


	@PreDestroy
	public void destroy() {
		if (cache != null){
			cache.close();
		}
		if (client != null) {
			log.info("zk has closed");
			client.close();
		}
	}
}
