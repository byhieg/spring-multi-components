package com.byhieg.dao.xml;

import com.byhieg.Application;
import com.byhieg.dao.entity.ClassNameEnum;
import com.byhieg.dao.entity.SexEnum;
import com.byhieg.dao.entity.Student;
import com.byhieg.dao.entity.StudentCourseRef;
import com.byhieg.dao.mapper.StudentCourseRefMapper;
import com.byhieg.dao.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest(classes = Application.class)
@ActiveProfiles("xml")
@MapperScan(basePackages = {"com.byhieg.dao.mapper"})
public class XmlMyBatisTest {

	private Logger log = LoggerFactory.getLogger(XmlMyBatisTest.class);

	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	StudentCourseRefMapper studentCourseRefMapper;


	@Test
	public void insertStudent() {
		Student student = new Student();
		student.setName("xiaoming");
		student.setAge(21);
		student.setClassName(ClassNameEnum.THREE);
		student.setCreateTime(new Date());
		student.setModifiedTime(new Date());
		student.setSex(SexEnum.male);
		Long affectRow = studentMapper.insertStudent(student);
		log.info("affectRow is {}", affectRow);
	}

	@Test
	public void selectAll() {
		log.info("student is {}", studentMapper.selectAll());
	}

	@Test
	public void selectByName() {
		log.info("student is {}", studentMapper.selectByName("xiaoming"));
	}

	@Test
	public void batchInsert(@Autowired SqlSessionFactory sqlSessionFactory) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			List<Student> students = buildStudents();
			for (Student student : students) {
				mapper.insertStudent(student);
			}
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Test
	public void selectAllInPage() {
		PageHelper.startPage(3,2);
		List<Student> students = studentMapper.selectAll();
		PageInfo<Student> studentPageInfo = new PageInfo<>(students);
		log.info("totalPages is {},total is {} student is {}", studentPageInfo.getPages(),studentPageInfo.getTotal(),studentPageInfo.getList());
	}

	public List<Student> buildStudents() {
		List<Student> students = Arrays.asList(
				new Student("xiaohu", SexEnum.male, 23, ClassNameEnum.ONE), 
				new Student("xiaotang", SexEnum.male, 23, ClassNameEnum.ONE), 
				new Student("xiaoting", SexEnum.female, 23, ClassNameEnum.ONE), 
				new Student("xiaoju", SexEnum.male, 23, ClassNameEnum.ONE), 
				new Student("dahu", SexEnum.male, 23, ClassNameEnum.TWO), 
				new Student("xiaofu", SexEnum.male, 23,ClassNameEnum.TWO), 
				new Student("yuting", SexEnum.male, 23, ClassNameEnum.TWO), 
				new Student("xiaohua", SexEnum.male, 22, ClassNameEnum.TWO), 
				new Student("xiaohong", SexEnum.male, 21, ClassNameEnum.THREE), 
				new Student("xiaoli", SexEnum.female, 21, ClassNameEnum.THREE), 
				new Student("xiaojuan", SexEnum.male, 21, ClassNameEnum.THREE));
		return students;
	}

	@Test
	public void insertStudentCourse() {
		StudentCourseRef studentCourseRef = new StudentCourseRef();
		studentCourseRef.setCourseId(1L);
		studentCourseRef.setStudentId(9L);
		studentCourseRef.setCreateTime(new Date());
		studentCourseRef.setModifiedTime(new Date());
		studentCourseRefMapper.insertStudentCourseRef(studentCourseRef);
	}

	@Test
	public void selectAllCourse() {
		List<StudentCourseRef> all = studentCourseRefMapper.selectAll();
		log.info("StudentCourseRef is {}", all);
	}


	@Test
	public void getStudentWithCourse() {
		List<Student> students = studentCourseRefMapper.getStudentWithCourse("xiaotang");
		log.info("students is {}",students);
	}

	@Test
	public void getStudentWithAddress() {
		List<Student> students = studentMapper.selectAllWithAddress();
		log.info("students is {}",students);
	}

}
