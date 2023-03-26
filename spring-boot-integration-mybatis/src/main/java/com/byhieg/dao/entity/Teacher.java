package com.byhieg.dao.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class Teacher {
	
	private Long id;
	private Date createTime;
	
	private Date modifiedTime;
	
	private String name;
	
	private Integer age;

	private SexEnum  sex;
	
	private List<Course> courses;
	
	private Address address;
	
	private List<Student> students;


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

	public SexEnum  getSex() {
		return sex;
	}

	public void setSex(SexEnum  sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Teacher(){
		
	}

	public Teacher(String name, SexEnum  sex, Integer age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.createTime = new Date();
		this.modifiedTime = new Date();
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", name='" + name + '\'' +
				", age=" + age +
				", sex=" + sex +
				", courses=" + courses +
				", address=" + address +
				", students=" + students +
				'}';
	}
}
