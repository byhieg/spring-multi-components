package com.byhieg.validator;

import com.byhieg.annotation.TelephoneNumberValidatorAnnotation;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by byhieg on 2023/4/15.
 * Mail to byhieg@gmail.com
 */
public class TelephoneNumberValidator implements ConstraintValidator<TelephoneNumberValidatorAnnotation, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value) || value.length() != 11) {
			return false;
		}
		if (!StringUtils.startsWithAny(value,new String[]{"131","133","134","135","136","137","138","139"})){
			return false;
		}
		return true;
	}
}
