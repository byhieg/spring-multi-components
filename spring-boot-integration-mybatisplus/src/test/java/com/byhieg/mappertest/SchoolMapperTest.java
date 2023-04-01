package com.byhieg.mappertest;

import com.byhieg.dao.entity.ContentEntity;
import com.byhieg.dao.entity.SchoolEntity;
import com.byhieg.dao.mapper.SchoolMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by byhieg on 2023/4/1.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest
public class SchoolMapperTest {

	private Logger logger = LoggerFactory.getLogger(SchoolMapper.class);
	
	@Resource
	SchoolMapper schoolMapper;
	
	@Test
	public void test(){
		SchoolEntity school = schoolMapper.selectById(1L);
		ContentEntity contentEntity = new ContentEntity();
		contentEntity.setName("xxSchool");
		contentEntity.setPrivateSchool(true);
		school.setContentEntity(contentEntity);
		school.insertOrUpdate();
	}
}
