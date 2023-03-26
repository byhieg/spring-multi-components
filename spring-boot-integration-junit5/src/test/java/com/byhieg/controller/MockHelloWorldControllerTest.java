package com.byhieg.controller;

import com.byhieg.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MockHelloWorldControllerTest {

	private Logger log = LoggerFactory.getLogger(MockHelloWorldControllerTest.class);
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		log.info("test begin");
	}

	@Test
	public void testGet() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/hello/get?content=world"))
				.andExpect(MockMvcResultMatchers.status()
						.isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string("{\"code\":200,\"data\":\"hello-world\",\"status\":\"success\",\"errorMsg\":null}"));
	}

}
