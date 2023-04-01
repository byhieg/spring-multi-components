package com.byhieg.servicetest;

import com.byhieg.dao.entity.ClassNameEnum;
import com.byhieg.dao.entity.StudentEntity;
import com.byhieg.service.IStudentService;
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
public class StudentServiceTest {

	private Logger logger = LoggerFactory.getLogger(StudentServiceTest.class);

	@Resource
	IStudentService studentService;

	@Test
	public void test() {
		StudentEntity student = new StudentEntity();
		student.setId(19L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
	}
}
