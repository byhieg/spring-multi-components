package com.byhieg.dao.mapper;

import com.byhieg.dao.entity.Student;
import com.byhieg.dao.entity.StudentCourseRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by byhieg on 2023/3/27.
 * Mail to byhieg@gmail.com
 */
@Mapper
public interface StudentCourseRefMapper {

	Long insertStudentCourseRef(StudentCourseRef studentCourseRef);

	List<StudentCourseRef> selectAll();

	List<Student> getStudentWithCourse(@Param("name") String studentName);

}
