package com.byhieg.dao.mapper;

import com.byhieg.dao.entity.Course;
import com.byhieg.dao.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@Mapper
public interface CourseMapper {

	@Insert({
			"insert into course (create_time, modified_time, name) values (#{createTime},#{modifiedTime},#{name})"
	})
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	Long insertCourse(Course course);

	@Select({
			"select * from course"
	})
	List<Course> selectAll();
	
	@Select({
			"select * from course where name = #{name} limit 1"
	})
	Course selectByName(@Param("name")String name);
}
