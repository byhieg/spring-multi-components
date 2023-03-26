package com.byhieg.component;

import com.byhieg.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class MockHelloHelperTest {

	private Logger log = LoggerFactory.getLogger(MockHelloHelperTest.class);
	@InjectMocks
	public HelloService helloService;
	
	@Mock
	HelloHelper helloHelper;

	@Test
	public void injectLog() {
		Mockito.doAnswer(invocationOnMock -> {
			log.info("hello method [{}] is called",(String)invocationOnMock.getArgument(0));
			return null;
		}).when(helloHelper).injectLog(Mockito.anyString());
		Assertions.assertEquals("hello-world",helloService.getHello("world"));
	}
}
