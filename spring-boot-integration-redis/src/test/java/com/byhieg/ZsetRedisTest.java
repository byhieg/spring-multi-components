package com.byhieg;

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
public class ZsetRedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testAdd() {
		String key = "test:zset";
		stringRedisTemplate.delete(key);
		stringRedisTemplate.opsForZSet().add(key,"byhieg",100);
		stringRedisTemplate.opsForZSet().add(key,"byhieg1",101);
		stringRedisTemplate.opsForZSet().add(key,"byhieg2",102);
		System.out.println((stringRedisTemplate.opsForZSet().reverseRange(key,0,1)));
	}
}
