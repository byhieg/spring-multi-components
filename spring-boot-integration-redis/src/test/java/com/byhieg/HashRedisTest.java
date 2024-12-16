package com.byhieg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by byhieg on 2023/4/16.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest
@ActiveProfiles("standAlone")
public class HashRedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testKey() {
		String key = "test:hash";
		stringRedisTemplate.delete(key);
		stringRedisTemplate.opsForHash().put(key,"a","1");
		Assertions.assertEquals("1",stringRedisTemplate.opsForHash().get(key,"a"));
	}
}
