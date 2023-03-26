package com.byhieg.dao.mapper;

import com.byhieg.dao.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@Mapper
public interface StudentMapper {

	Long insertStudent(Student student);

	List<Student> selectAll();

	List<Student> selectByName(String name);

	List<Student> selectAllWithAddress();

}
