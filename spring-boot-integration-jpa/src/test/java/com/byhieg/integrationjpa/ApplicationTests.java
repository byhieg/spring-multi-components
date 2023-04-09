package com.byhieg.integrationjpa;

import com.byhieg.dao.entity.ClassNameEnum;
import com.byhieg.dao.entity.SexEnum;
import com.byhieg.dao.entity.StudentEntity;
import com.byhieg.dao.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ApplicationTests {

	@Autowired
	TransactionTemplate transactionTemplate;

	@Autowired
	StudentRepository studentRepository;

	@Test
	public void selectAll() throws Exception {
		List<StudentEntity> students = studentRepository.findAll();
		log.info("students is {}", students);
	}

	@Test
	public void insert() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setClassName(ClassNameEnum.THREE.getValue());
		student.setName("小王");
		student.setSex(SexEnum.male.name());
		student.setAddressId(1L);
		student.setAge(18);
		student.setCreateTime(new Date());
		student.setModifiedTime(new Date());
		
		studentRepository.save(student);
	}

	/**
	 * 这里的save方法，虽然是查询到db的数据，然后在set。但是实际的sql 语句，只更新了set的参数。
	 * Hibernate: update student set age=?, modified_time=? where id=?
	 */
	@Test
	public void update() throws Exception{
		Optional<StudentEntity> optionalStudent = studentRepository.findById(18L);
		optionalStudent.ifPresent(item->{
			item.setAge(19);
			item.setModifiedTime(new Date());
			studentRepository.save(item);
		});
	}
	
	@Test
	public void select() throws Exception{
		List<StudentEntity> studentEntities = studentRepository.findAll();
		log.info("students is [{}]", studentEntities);
		studentEntities = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedTime"));
		log.info("students is [{}]", studentEntities);
	}
	
	@Test
	public void selectBySpecification() throws Exception{
		List<StudentEntity> studentEntities;
		Specification<StudentEntity> spec = (root, query, cb) -> {
			Path<Object> age = root.get("age");
			Path<Object> sex = root.get("sex");
			Predicate agePredicate = cb.equal(age, 23);
			Predicate sexPredicate = cb.equal(sex, SexEnum.male.name());
			return cb.and(agePredicate, sexPredicate);
		};
		studentEntities = studentRepository.findAll(spec);
		log.info("students is [{}]", studentEntities);

		spec = (root,query,cb)->{
			Path<Object> name = root.get("name");
			return cb.like(name.as(String.class), "xiao%");
		};
		studentEntities = studentRepository.findAll(spec);
		log.info("students is [{}]", studentEntities);

		spec = (root,query,cb)->{
			Path<Object> age = root.get("age");
			return cb.ge(age.as(Integer.class), 19);
		};
		studentEntities = studentRepository.findAll(spec);
		log.info("students is [{}]", studentEntities);

		Pageable pageable = PageRequest.of(1, 2);
		Page<StudentEntity> page = studentRepository.findAll(spec, pageable);

		log.info("{} {} {}", page.getContent(), page.getTotalElements(), page.getTotalPages());
	}

	@Test
	public void selectByExample() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setAge(23);
		Example<StudentEntity> example = Example.of(student);
		// where studentent0_.age=23
		List<StudentEntity> studentEntities = studentRepository.findAll(example);
		log.info("students is [{}]", studentEntities);
		
		student.setSex(SexEnum.male.name());
		example = Example.of(student,ExampleMatcher.matchingAny());
		//where studentent0_.age=23 or studentent0_.sex=?
		studentEntities = studentRepository.findAll(example);
		log.info("students is [{}]", studentEntities);
		
		student = new StudentEntity();
		student.setName("xiao");
		example = Example.of(student, ExampleMatcher.matching().withMatcher("name",
				ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.STARTING)));
		//where studentent0_.age=23 or studentent0_.name like xiao%
		studentEntities = studentRepository.findAll(example);
		log.info("students is [{}]", studentEntities);
	}

	@Test
	public void interfaceSqlTest() {
		log.info("students is [{}]", studentRepository.findByNameStartingWith("xiao"));
		log.info("students is [{}]", studentRepository.findByAgeGreaterThan(22));
		log.info("students is [{}]", studentRepository.findByAge(22));
		log.info("students is [{}]", studentRepository.findAllByIds(Arrays.asList(4L,5L,6L,7L,8L,9L)));
	}
}
