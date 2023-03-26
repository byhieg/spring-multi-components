package com.byhieg.dao;

import com.byhieg.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class)
@ActiveProfiles("testdb")
public class H2UserMapperTest {
	
	@Autowired
	UserMapper userMapper;

	private static Logger logger = LoggerFactory.getLogger(H2UserMapperTest.class);
	
	@Test
	void selectAll() {
		Assertions.assertTrue(userMapper.selectAll().size() == 1);
	}
	
	@Test
	void insertUser(){
		User user = new User();
		user.setName("li ming");
		Integer affectRow = userMapper.insertUser(user);
		Assertions.assertTrue(affectRow > 0);
		Assertions.assertTrue(userMapper.selectAll().size() == 2);
	}
}

