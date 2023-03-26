package com.byhieg.component;

import com.byhieg.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class)
class HelloHelperTest {

	@Autowired
	private HelloHelper helloHelper;
	
	@Test
	void injectLog() {
		helloHelper.injectLog("getHello");
	}
	
	
}