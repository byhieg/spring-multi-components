package com.byhieg.springbootintegrationzk;

import com.byhieg.springbootintegrationzk.components.ZkOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringBootIntegrationZkApplicationTests {

	@Autowired
	ZkOperator zkOperator;

	@Test
	@Order(1)
	void checkZkConnect() {
		log.info("{}", zkOperator.getClient().getState().name());
	}

	@Test
	@Order(2)
	void createNode() throws Exception {
		String nodePath = "/byhieg";
		String data = "hello world";
		// 创建持久节点
		if (zkOperator.getClient().checkExists().forPath(nodePath) == null) {
			zkOperator.getClient().create().creatingParentsIfNeeded()
					.withMode(CreateMode.PERSISTENT)
					.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
					.forPath(nodePath, data.getBytes());
		}
		// 创建持久顺序节点
		zkOperator.getClient().create().creatingParentsIfNeeded()
				.withMode(CreateMode.PERSISTENT_SEQUENTIAL)
				.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
				.forPath(nodePath, data.getBytes());
		String tmpNodePath = "/byhieg-tmp";
		// 创建临时节点
		zkOperator.getClient().create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
				.forPath(tmpNodePath, data.getBytes());
		// 创建临时顺序节点
		zkOperator.getClient().create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
				.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
				.forPath(tmpNodePath, data.getBytes());
		Thread.sleep(10000);
	}

	@Test
	@Order(3)
	void getAndUpdateNode() throws Exception {
		// 基于version 获取节点，并更新节点内容
		String node = "/byhieg";
		Stat stat = new Stat();
		byte[] data = zkOperator.getClient().getData().storingStatIn(stat).forPath(node);
		log.info("data is {},version is {}", new String(data), stat.getVersion());
		data = "hello world!!!".getBytes();
		zkOperator.getClient().setData().withVersion(stat.getVersion()).forPath(node, data);
		log.info("data is {},version is {}", new String(data), stat.getVersion());
		try {
			zkOperator.getClient().setData().withVersion(stat.getVersion()).forPath(node, data);
		} catch (KeeperException.BadVersionException e) {
			log.error("update with error version,it may be updated by other,fetch new value to update", e);
			stat = new Stat();
			zkOperator.getClient().getData().storingStatIn(stat).forPath(node);
			data = "hello world!!!".getBytes();
			zkOperator.getClient().setData().withVersion(stat.getVersion()).forPath(node, data);
			log.info("new data is {},version is {}", new String(data), stat.getVersion());
		}
	}

	@Test
	@Order(4)
	void getChildren() throws Exception {
		List<String> childNodes = zkOperator.getClient().getChildren().forPath("/");
		for (String childNode : childNodes) {
			log.info("childNode is {}", childNode);
		}
	}

	@Test
	@Order(100)
	void deleteNode() throws Exception {
		Stat stat = new Stat();
		zkOperator.getClient().getData().storingStatIn(stat).forPath("/");
		zkOperator.getClient().delete().withVersion(stat.getVersion()).forPath("/");
	}

}
