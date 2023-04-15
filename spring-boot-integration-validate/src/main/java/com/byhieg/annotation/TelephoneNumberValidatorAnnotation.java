package com.byhieg.annotation;

import com.byhieg.validator.TelephoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by byhieg on 2023/4/15.
 * Mail to byhieg@gmail.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneNumberValidator.class)
public @interface TelephoneNumberValidatorAnnotation {


	String message() default "{TelephoneNumberValidatorAnnotation.message}";

	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
