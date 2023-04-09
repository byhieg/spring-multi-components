package com.byhieg.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

/**
 * Created by byhieg on 2023/4/5.
 * Mail to byhieg@gmail.com
 */
public class JpaUtil {
	public static void copyNotNullProperties(Object src,Object target){
		BeanUtils.copyProperties(src,target,getNullPropertyNames(src));
	}

	private static String[] getNullPropertyNames(Object object) {
		final BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
		return Stream.of(wrapper.getPropertyDescriptors())
				.map(PropertyDescriptor::getName)
				.filter(propertyName -> wrapper.getPropertyValue(propertyName) == null)
				.toArray(String[]::new);
	}
}
