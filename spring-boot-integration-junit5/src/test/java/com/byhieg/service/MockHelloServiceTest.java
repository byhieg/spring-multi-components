package com.byhieg.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */

@ExtendWith(MockitoExtension.class)
public class MockHelloServiceTest {
	
	@Mock
	HelloService helloService;
	@Test
	void getHello() {
		Mockito.when(helloService.getHello(Mockito.anyString())).thenReturn("hello-world");
		Assertions.assertEquals("hello-world", helloService.getHello("world"));
	}
}
