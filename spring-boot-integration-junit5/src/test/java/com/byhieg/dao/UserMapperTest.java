package com.byhieg.dao;

import com.byhieg.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class)
class UserMapperTest {
	private Logger log = LoggerFactory.getLogger(UserMapperTest.class);
	
	@Autowired
	UserMapper userMapper;

	@Test
	void insertUser() {
		User user = new User();
		user.setName("li ming");
		Integer affectRow = userMapper.insertUser(user);
		Assertions.assertTrue(affectRow > 0);
	}

	@Test
	void selectAll() {
		Assertions.assertTrue(userMapper.selectAll().size() > 0);
	}
}