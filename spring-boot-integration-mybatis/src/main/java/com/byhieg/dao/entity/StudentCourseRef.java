package com.byhieg.dao.entity;

import com.byhieg.dao.annotation.CreateTime;
import com.byhieg.dao.annotation.ModifiedTime;

import java.util.Date;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class StudentCourseRef {
	
	private Long id;
	@CreateTime
	private Date createTime;
	
	@ModifiedTime
	private Date modifiedTime;
	
	private Long studentId;
	
	private Long courseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "StudentCourseRef{" +
				"id=" + id +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", studentId=" + studentId +
				", courseId=" + courseId +
				'}';
	}
}
