package com.byhieg.dao.entity;

import java.util.Arrays;

/**
 * Created by byhieg on 2023/4/5.
 * Mail to byhieg@gmail.com
 */
public enum ClassNameEnum {

	ONE("二年一班"),
	TWO("二年二班"),
	THREE("二年三班");

	private String value;

	ClassNameEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ClassNameEnum getClassName(String value) {
		return Arrays.stream(ClassNameEnum.values()).filter(i -> i.getValue().equals(value)).findAny().orElse(null);
	}
}
