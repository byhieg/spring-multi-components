package com.byhieg.dao.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class Student {
	
	private Long id;
	private Date createTime;
	
	private Date modifiedTime;
	
	private String name;
	
	private SexEnum sex;
	
	private Integer age;
	
	private ClassNameEnum className;
	
	private List<Course>  courses;
	
	private Address address;

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

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public ClassNameEnum getClassName() {
		return className;
	}

	public void setClassName(ClassNameEnum className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", name='" + name + '\'' +
				", sex=" + sex +
				", age=" + age +
				", className=" + className +
				", courses=" + courses +
				", address=" + address +
				'}';
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Student() {
		
	}

	public Student(String name, SexEnum sex, Integer age, ClassNameEnum className) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.className = className;
		this.createTime = new Date();
		this.modifiedTime = new Date();
	}
}
