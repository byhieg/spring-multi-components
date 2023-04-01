package com.byhieg.dao.entity;

import java.io.Serializable;

/**
 * Created by byhieg on 2023/4/1.
 * Mail to byhieg@gmail.com
 */

public class ContentEntity implements Serializable {
	
	private String name;
	private Boolean privateSchool;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPrivateSchool() {
		return privateSchool;
	}

	public void setPrivateSchool(Boolean privateSchool) {
		this.privateSchool = privateSchool;
	}

	@Override
	public String toString() {
		return "ContentEntity{" +
				"name='" + name + '\'' +
				", privateSchool=" + privateSchool +
				'}';
	}
}
