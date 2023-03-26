package com.byhieg.dao.entity;

import com.byhieg.dao.annotation.CreateTime;
import com.byhieg.dao.annotation.ModifiedTime;

import java.util.Date;

/**
 * Created by byhieg on 2023/3/27.
 * Mail to byhieg@gmail.com
 */
public class Address {
	
	private Long id;
	@CreateTime
	private Date createTime;
	@ModifiedTime
	private Date modifiedTime;
	
	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", address='" + address + '\'' +
				'}';
	}
}
