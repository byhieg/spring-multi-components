package com.byhieg.dao.sql;

import com.byhieg.dao.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class SqlContent {

	public String select(@Param("teacher") Teacher teacher) {
		return new SQL() {
			{
				SELECT("*");
				FROM("teacher");
				if (teacher.getAge() != null && teacher.getAge() > 0) {
					WHERE(" age=#{teacher.age} ");
				}
				if (teacher.getName() != null && teacher.getName() != "") {
					WHERE(" name=#{teacher.name} ");
				}
				if (teacher.getSex() != null) {
					WHERE(" sex=#{teacher.sex} ");
				}
			}

		}.toString();
	}
}
