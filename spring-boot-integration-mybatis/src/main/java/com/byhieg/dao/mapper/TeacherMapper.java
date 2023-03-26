package com.byhieg.dao.mapper;

import com.byhieg.dao.entity.Address;
import com.byhieg.dao.entity.Course;
import com.byhieg.dao.entity.Student;
import com.byhieg.dao.entity.Teacher;
import com.byhieg.dao.sql.SqlContent;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SqlBuilder;
import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@Mapper
public interface TeacherMapper {

	@Insert({
			"insert into teacher (create_time, modified_time, name, sex, age)\n" +
					"        values (#{createTime}, #{modifiedTime}, #{name}, #{sex}, #{age})"
	})
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	Long insertTeacher(Teacher teacher);

	@Select({
			"select * from teacher"
	})
	@Results(id = "teacherMap", value = {
//			@Result(column="create_time", property="createTime"),
//			@Result(column="modified_time", property="modifiedTime"),
	})
	List<Teacher> selectAll();

	@Select({
			"select * from teacher where name = #{name} limit 1"
	})
	Teacher selectByName(@Param("name") String name);


	@SelectProvider(type = SqlContent.class, method = "select")
	List<Teacher> select(@Param("teacher") Teacher teacher);


	@Select({
			"select * from teacher where name = #{name}"
	})
	@Results(id = "teacherCourse", value = {
			@Result(property = "courses", javaType = ArrayList.class, many = @Many(select = "selectCourseByTeacherId"), column = "id")
	})
	List<Teacher> selectWithCourse(@Param("name") String name);


	@Select({
			"select course.* from course,teacher_course_ref where teacher_course_ref.teacher_id = #{teacherId} and teacher_course_ref.course_id = course.id"
	})
	List<Course> selectCourseByTeacherId(@Param("teacherId") Long teacherId);


	@Select({
			"select * from teacher where name = #{name}"
	})
	@Results(id = "teacherAddress", value = {
			@Result(id = true, column = "id", property = "id"),
			@Result(property = "address", javaType = Address.class, one = @One(select = "selectAddressById"), column = "id")
	})
	List<Teacher> selectWithAddress(@Param("name") String name);


	@Select({
			"select * from address where id = #{addressId}"
	})
	Address selectAddressById(@Param("id") Long addressId);


	@Select({
			"select * from teacher where name = #{name}"
	})
	@Results(id = "teacherStudent", value = {
			@Result(id = true, column = "id", property = "id"),
			@Result(property = "students", javaType = ArrayList.class, many = @Many(select = "selectStudentByTeacherId"), column = "id")
	})
	List<Teacher> selectStudentWithTeacher(@Param("name") String name);


	@Select({
			"select * from student,student_course_ref,teacher_course_ref " +
					"where teacher_course_ref.teacher_id=#{teacherId} " +
					"and student_course_ref.course_id = teacher_course_ref.course_id " +
					"and student.id = student_course_ref.student_id"
	})
	List<Student> selectStudentByTeacherId(@Param("teacherId") Long teacherId);

}
