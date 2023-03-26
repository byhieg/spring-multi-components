package com.byhieg.service;

import com.byhieg.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class)
class HelloServiceTest {
	
	@Resource
	HelloService helloService;
	
	@Test
	void getHello() {
		Assertions.assertEquals("hello-world", helloService.getHello("world"));
	}
}