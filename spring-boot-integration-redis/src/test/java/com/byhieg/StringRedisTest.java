package com.byhieg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by byhieg on 2023/4/16.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest
@ActiveProfiles("standAlone")
public class StringRedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testAddStr() {
		String key = "test:name";
		stringRedisTemplate.opsForValue().set(key, "byhieg");
		Assertions.assertEquals(stringRedisTemplate.opsForValue().getAndDelete(key), "byhieg");
	}

	@Test
	public void testInc() {
		String key = "test:number";
		for (int i = 0; i < 10; i++) {
			stringRedisTemplate.opsForValue().increment(key, 1);
		}
		Assertions.assertEquals(stringRedisTemplate.opsForValue().getAndDelete(key), "10");
		stringRedisTemplate.delete(key);
	}

	@Test
	public void testExpire() throws InterruptedException {
		String key = "test:expire_key";
		stringRedisTemplate.opsForValue().set(key, "byhieg", 5, TimeUnit.SECONDS);
		Thread.sleep(6 * 1000);
		Assertions.assertNull(stringRedisTemplate.opsForValue().get(key));
		stringRedisTemplate.opsForValue().set(key, "byhieg");
		stringRedisTemplate.expire(key, 5, TimeUnit.SECONDS);
		Thread.sleep(6 * 1000);
		Assertions.assertNull(stringRedisTemplate.opsForValue().get(key));
	}

	@Test
	public void testMultiKey() throws InterruptedException {
		String key = "test:multi_key";
		Map<String, String> collections = new HashMap<>();
		collections.put(key + "1","byhieg1");
		collections.put(key + "2","byhieg2");
		collections.put(key + "3","byhieg3");
		collections.put(key + "4","byhieg4");
		stringRedisTemplate.opsForValue().multiSet(collections);
		List<String> res = stringRedisTemplate.opsForValue().multiGet(Arrays.asList(key + "1",key,key + "2"));
		Assertions.assertEquals(res.get(0),"byhieg1");
		Assertions.assertNull(res.get(1),"byhieg1");
		Assertions.assertEquals(res.get(2),"byhieg2");
	}

	@Test
	public void testIfAbsent() {
		String key = "test:exist_key";
		stringRedisTemplate.opsForValue().getAndDelete(key);
		Assertions.assertEquals(Boolean.TRUE, stringRedisTemplate.opsForValue().setIfAbsent(key, "byhieg"));
		Assertions.assertNotEquals(Boolean.TRUE, stringRedisTemplate.opsForValue().setIfAbsent(key, "byhieg"));
	}

	@Test
	public void testIfPresent() {
		String key = "test:exist_key";
		stringRedisTemplate.opsForValue().getAndDelete(key);
		stringRedisTemplate.opsForValue().set(key,"byhieg");
		Assertions.assertEquals(Boolean.TRUE, stringRedisTemplate.opsForValue().setIfPresent(key, "byhieg"));
	}
}
