package com.byhieg.dao.entity;

import com.byhieg.dao.annotation.CreateTime;
import com.byhieg.dao.annotation.ModifiedTime;

import java.util.Date;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class Course {
	
	private Long id;
	@CreateTime
	private Date createTime;
	@ModifiedTime
	private Date modifiedTime;
	
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", name='" + name + '\'' +
				'}';
	}
}
