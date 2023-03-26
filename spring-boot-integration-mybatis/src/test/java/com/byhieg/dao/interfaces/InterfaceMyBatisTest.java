package com.byhieg.dao.interfaces;

import com.byhieg.Application;
import com.byhieg.dao.entity.Address;
import com.byhieg.dao.entity.Course;
import com.byhieg.dao.entity.SexEnum;
import com.byhieg.dao.entity.Teacher;
import com.byhieg.dao.mapper.AddressMapper;
import com.byhieg.dao.mapper.CourseMapper;
import com.byhieg.dao.mapper.StudentMapper;
import com.byhieg.dao.mapper.TeacherMapper;
import com.byhieg.dao.xml.XmlMyBatisTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class)
@ActiveProfiles("interface")
public class InterfaceMyBatisTest {

	private Logger log = LoggerFactory.getLogger(InterfaceMyBatisTest.class);

	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	CourseMapper courseMapper;
	
	@Autowired
	AddressMapper addressMapper;

	@Test
	public void insertTeacher() {
		Teacher teacher = new Teacher("张老师", SexEnum.male, 34);
		Long affectRows = teacherMapper.insertTeacher(teacher);
		log.info("affectrows is {}", affectRows);
	}

	@Test
	public void selectAll() {
		List<Teacher> teachers = teacherMapper.selectAll();
		log.info("teachers is {}",teachers);
	}

	@Test
	public void selectByName() {
		Teacher teacher = teacherMapper.selectByName("张老师");
		log.info("teachers is {}",teacher);
	}

	@Test
	public void select() {
		Teacher teacher = new Teacher();
		teacher.setName("张老师");
		List<Teacher> list = teacherMapper.select(teacher);
		log.info("teachers is {}",list);
	}

	@Test
	public void insertCourse() {
		Course course = new Course();
		course.setName("数学");
		Long affectRows = courseMapper.insertCourse(course);
		log.info("affectrows is {}", affectRows);

	}

	@Test
	public void courseByName() {
		Course course = courseMapper.selectByName("数学");
		log.info("course is {}", course);
	}

	@Test
	public void insertAddress() {
		Address address = new Address();
		address.setAddress("北京市海淀区");
		addressMapper.insertAddress(address);
	}

	@Test
	public void selectWithCourse() {
		List<Teacher> teachers = teacherMapper.selectWithCourse("张老师");
		log.info("teachers is {}",teachers);
	}

	@Test
	public void selectWithAddress() {
		List<Teacher> teachers = teacherMapper.selectWithAddress("张老师");
		log.info("teachers is {}",teachers);
	}

	@Test
	public void selectStudentWithTeacher() {
		List<Teacher> teachers = teacherMapper.selectStudentWithTeacher("张老师");
		teachers.forEach(teacher -> {
			if (!CollectionUtils.isEmpty(teacher.getStudents())) {
				log.info("student size is {}", teacher.getStudents().size());
				teacher.getStudents().forEach(student -> {
					log.info("student id is {}",student.getId());
				});
			}
			
		});

	}

}
