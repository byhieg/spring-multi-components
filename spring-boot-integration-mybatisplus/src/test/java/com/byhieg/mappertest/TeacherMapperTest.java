package com.byhieg.mappertest;

import com.byhieg.MybatisPlusTest;
import com.byhieg.dao.entity.SexEnum;
import com.byhieg.dao.entity.TeacherEntity;
import com.byhieg.dao.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by byhieg on 2023/4/1.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest
public class TeacherMapperTest {

	private Logger log = LoggerFactory.getLogger(MybatisPlusTest.class);

	@Resource
	TeacherMapper teacherMapper;

	@Test
	public void enumTest() {
		TeacherEntity teacher = teacherMapper.selectById(1);
		log.info("{}",teacher);

		TeacherEntity newTeacher = new TeacherEntity().setAge(30).setAddressId(1L).setName("王老师").setSex(SexEnum.male);
		newTeacher.insertOrUpdate();
	}
}
