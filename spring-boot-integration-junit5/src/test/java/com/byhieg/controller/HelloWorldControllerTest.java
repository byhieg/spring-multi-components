package com.byhieg.controller;

import com.byhieg.Application;
import com.byhieg.entity.WebResponse;
import com.byhieg.utils.ResponseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldControllerTest {
	private Logger log = LoggerFactory.getLogger(HelloWorldControllerTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void getWithTestRestTemplate() {
		WebResponse actual = restTemplate.getForObject("/hello/get?content=world", WebResponse.class);
		Assertions.assertEquals(200, actual.getCode());
		Assertions.assertEquals(ResponseUtil.SUCCESS_STATUS, actual.getStatus());
		Assertions.assertEquals("hello-world", actual.getData());
	}
}