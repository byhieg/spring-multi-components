package com.byhieg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by byhieg on 2023/4/16.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest
@ActiveProfiles("standAlone")
public class SetRedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testIntersect() {
		String key1 = "test:set1";
		stringRedisTemplate.delete(key1);
		stringRedisTemplate.opsForSet().add(key1,"1","2","3","4");
		Assertions.assertEquals(4,stringRedisTemplate.opsForSet().size(key1));

		String key2 = "test:set2";
		stringRedisTemplate.delete(key2);
		stringRedisTemplate.opsForSet().add(key2,"3","4","5","6");
		Assertions.assertEquals(4,stringRedisTemplate.opsForSet().size(key2));

		Set<String> res = stringRedisTemplate.opsForSet().intersect(key1, key2);
		Set<String> expected = new HashSet<>();
		expected.add("3");
		expected.add("4");
		Assertions.assertEquals(res,expected);
	}

	@Test
	public void testUnion() {
		String key1 = "test:set1";
		stringRedisTemplate.delete(key1);
		stringRedisTemplate.opsForSet().add(key1,"1","2","3","4");
		Assertions.assertEquals(4,stringRedisTemplate.opsForSet().size(key1));

		String key2 = "test:set2";
		stringRedisTemplate.delete(key2);
		stringRedisTemplate.opsForSet().add(key2,"3","4","5","6");
		Assertions.assertEquals(4,stringRedisTemplate.opsForSet().size(key2));

		Set<String> res = stringRedisTemplate.opsForSet().union(key1, key2);
		Set<String> expected = new HashSet<>();
		for (int i = 1; i <= 6 ;i ++ ){
			expected.add(i + "");
		}
		Assertions.assertEquals(res,expected);
	}
	
	
	@Test
	public void testDifference(){
		String key1 = "test:set1";
		stringRedisTemplate.delete(key1);
		stringRedisTemplate.opsForSet().add(key1,"1","2","3","4");
		Assertions.assertEquals(4,stringRedisTemplate.opsForSet().size(key1));

		String key2 = "test:set2";
		stringRedisTemplate.delete(key2);
		stringRedisTemplate.opsForSet().add(key2,"3","4","5","6");
		Assertions.assertEquals(4,stringRedisTemplate.opsForSet().size(key2));

		Set<String> res = stringRedisTemplate.opsForSet().difference(key1, key2);
		Set<String> expected = new HashSet<>();
		expected.add("1");
		expected.add("2");
		Assertions.assertEquals(res,expected);
	}

	@Test
	public void testOther(){
		String key = "test:set";
		stringRedisTemplate.delete(key);
		stringRedisTemplate.opsForSet().add(key,"1","2","3","4");
		Assertions.assertEquals(Boolean.TRUE, stringRedisTemplate.opsForSet().isMember(key, "1"));
		Set<String> expected = new HashSet<>();
		for (int i = 1; i <= 4 ;i ++ ){
			expected.add(i + "");
		}
		Assertions.assertEquals(expected,stringRedisTemplate.opsForSet().members(key));
		stringRedisTemplate.opsForSet().remove(key,"3","4");
		expected = new HashSet<>();
		for (int i = 1; i <= 2 ;i ++ ){
			expected.add(i + "");
		}
		Assertions.assertEquals(expected,stringRedisTemplate.opsForSet().members(key));
		
	}
}
