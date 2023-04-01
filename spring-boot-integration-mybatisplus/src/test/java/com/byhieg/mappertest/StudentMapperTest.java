package com.byhieg.mappertest;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.byhieg.MybatisPlusTest;
import com.byhieg.dao.entity.ClassNameEnum;
import com.byhieg.dao.entity.SexEnum;
import com.byhieg.dao.entity.StudentEntity;
import com.byhieg.dao.mapper.StudentMapper;
import org.apache.ibatis.util.MapUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by byhieg on 2023/4/1.
 * Mail to byhieg@gmail.com
 */
@SpringBootTest
public class StudentMapperTest {
	private Logger log = LoggerFactory.getLogger(MybatisPlusTest.class);
	@Resource
	private StudentMapper studentMapper;

	@Test
	public void selectById() {
		log.info("student is {}", studentMapper.selectById(1L));
	}

	@Test
	public void insertStudent() {
		StudentEntity student = new StudentEntity();
		student.setName("小红");
		student.setAddressId(1L);
		student.setSex(SexEnum.female.name());
		student.setClassName(ClassNameEnum.ONE.name());
		student.setAge(18);
		int rows = studentMapper.insert(student);
		log.info("rows is {} {}",rows,student.getId());
	}

	@Test
	public void updateStudent() {
		StudentEntity student = new StudentEntity();
		student.setId(19L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		int rows = studentMapper.updateById(student);
		log.info("rows is {} {}",rows,student.getId());
	}

	@Test
	public void updateStudentWithWhere1() {
		StudentEntity student = new StudentEntity();
		student.setClassName(ClassNameEnum.THREE.getValue());
		// 这个写法，modified_time 生效
		int rows = studentMapper.update(student, new UpdateWrapper<StudentEntity>().eq("id",19));
		log.info("rows is {} {}",rows,student.getId());
	}

	@Test
	public void updateStudentWithWhere2() {
		// 这个写法，modified_time 不生效
		studentMapper.update(null,new UpdateWrapper<StudentEntity>().eq("id",19).set("class_name",ClassNameEnum.TWO.getValue()));
	}

	@Test
	public void select() {
		log.info("selectById : {}", studentMapper.selectById(19));
		log.info("selectList null : {}", studentMapper.selectList(null));
		log.info("selectList : {}", studentMapper.selectList(new QueryWrapper<StudentEntity>().eq("sex",SexEnum.female.name())));
		log.info("selectBatchIds : {}", studentMapper.selectBatchIds(Arrays.asList(18, 19)));
		log.info("select By maps : {}",studentMapper.selectByMap(new HashMap(){{
			put("id",19);
		}}));
		log.info("select maps : {}", studentMapper.selectMaps(new QueryWrapper<StudentEntity>().eq("sex", SexEnum.female.name())));
		//注意： 只返回第一个字段的值
		log.info("select objs : {}",studentMapper.selectObjs(new QueryWrapper<StudentEntity>().eq("sex", SexEnum.female.name())));
		Page<StudentEntity> page = new Page<>(1, 2);
		page.addOrder(OrderItem.asc("age"));

		Page<StudentEntity> result = studentMapper.selectPage(page, Wrappers.<StudentEntity>lambdaQuery().eq(StudentEntity::getSex,SexEnum.female.name()));
		log.info("select page {},{},{},{}",result.getCurrent(),result.getSize(),result.getTotal(),result.getRecords());
	}

	@Test
	public void activeRecode() {
		StudentEntity student = new StudentEntity();
		student.setId(19L);
		student.setClassName(ClassNameEnum.THREE.getValue());
		student.insertOrUpdate();
		student = student.selectOne(Wrappers.<StudentEntity>lambdaQuery().eq(StudentEntity::getId, 19));
		log.info("student");
	}
}
