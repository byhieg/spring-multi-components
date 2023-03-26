package com.byhieg.utils;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class NameUtil {

	public static String generate(String prefix, String name) {
		return String.format("%s-%s", prefix, name);
	}
}
