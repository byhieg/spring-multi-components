package com.byhieg.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author byhieg
 * @since 2023-04-01
 */
@TableName("student")
public class StudentEntity extends Model<StudentEntity> {

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@TableField(value = "modified_time", fill = FieldFill.INSERT_UPDATE)
	private Date modifiedTime;

	/**
	 * 姓名
	 */
	@TableField("name")
	private String name;

	/**
	 * 性别
	 */
	@TableField("sex")
	private String sex;

	/**
	 * 年龄
	 */
	@TableField("age")
	private Integer age;

	/**
	 * 班级
	 */
	@TableField("class_name")
	private String className;

	/**
	 * 地址表的id
	 */
	@TableField("address_id")
	private Long addressId;

	public Date getCreateTime() {
		return createTime;
	}

	public StudentEntity setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public StudentEntity setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
		return this;
	}

	public String getName() {
		return name;
	}

	public StudentEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getSex() {
		return sex;
	}

	public StudentEntity setSex(String sex) {
		this.sex = sex;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public StudentEntity setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getClassName() {
		return className;
	}

	public StudentEntity setClassName(String className) {
		this.className = className;
		return this;
	}

	public Long getAddressId() {
		return addressId;
	}

	public StudentEntity setAddressId(Long addressId) {
		this.addressId = addressId;
		return this;
	}

	@Override
	public Serializable pkVal() {
		return id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StudentEntity{" +
				"id=" + id +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", age=" + age +
				", className='" + className + '\'' +
				", addressId=" + addressId +
				'}';
	}
}
