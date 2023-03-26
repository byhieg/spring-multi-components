package com.byhieg.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
class NameUtilTest {
	private Logger log = LoggerFactory.getLogger(NameUtilTest.class);
	@BeforeEach
	void setUp() {
		log.info("begin to test");
	}

	@AfterEach
	void tearDown() {
		log.info("end to test");
	}

	@Test
	void generate() {
		Assertions.assertEquals("spring-test", NameUtil.generate("spring", "test"));
		Assertions.assertNotEquals("springtest", NameUtil.generate("spring", "test"));
	}
}