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
public class ListRedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testQueue() {
		String key = "test:queue";
		stringRedisTemplate.delete(key);
		stringRedisTemplate.opsForList().leftPush(key, "1");
		stringRedisTemplate.opsForList().leftPush(key, "2");
		stringRedisTemplate.opsForList().leftPush(key, "3");
		stringRedisTemplate.opsForList().leftPush(key, "4");
		Assertions.assertEquals(4, stringRedisTemplate.opsForList().size(key));
		
		Assertions.assertEquals("1", stringRedisTemplate.opsForList().rightPop(key));
		Assertions.assertEquals("2", stringRedisTemplate.opsForList().rightPop(key));
		Assertions.assertEquals("3", stringRedisTemplate.opsForList().rightPop(key));
		Assertions.assertEquals("4", stringRedisTemplate.opsForList().rightPop(key));
	}

	@Test
	public void testStack() {
		String key = "test:stack";
		stringRedisTemplate.delete(key);
		stringRedisTemplate.opsForList().leftPush(key, "1");
		stringRedisTemplate.opsForList().leftPush(key, "2");
		stringRedisTemplate.opsForList().leftPush(key, "3");
		stringRedisTemplate.opsForList().leftPush(key, "4");
		Assertions.assertEquals(4, stringRedisTemplate.opsForList().size(key));

		Assertions.assertEquals("4", stringRedisTemplate.opsForList().leftPop(key));
		Assertions.assertEquals("3", stringRedisTemplate.opsForList().leftPop(key));
		Assertions.assertEquals("2", stringRedisTemplate.opsForList().leftPop(key));
		Assertions.assertEquals("1", stringRedisTemplate.opsForList().leftPop(key));
	}

	@Test
	public void testRemove() {
		String key = "test:list";
		stringRedisTemplate.delete(key);
		stringRedisTemplate.opsForList().leftPush(key, "1");
		stringRedisTemplate.opsForList().leftPush(key, "2");
		stringRedisTemplate.opsForList().leftPush(key, "3");
		stringRedisTemplate.opsForList().leftPush(key, "4");
		stringRedisTemplate.opsForList().remove(key,1,"2");
		Assertions.assertEquals(3, stringRedisTemplate.opsForList().size(key));
	}
}
