package com.byhieg.dto;

import com.byhieg.annotation.TelephoneNumberValidatorAnnotation;
import com.byhieg.dto.group.UpdateGroup;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * Created by byhieg on 2023/4/15.
 * Mail to byhieg@gmail.com
 */
@Data
public class ExampleEntity {

	@NotEmpty(message = "name is empty")
	@Pattern(regexp = "^([\\u4e00-\\u9fa5]{2,20}|[a-zA-Z.\\s]{2,20})$",message = "name is chinese name or english name like a.b or a b")
	private String name;

	@Max(value = 200, message = "age more than 200")
	@Min(value = 0, message = "age less than 0")
	private Integer age;
	
	@Email(message = "email not legal format")
	@NotEmpty(message = "email not empty")
	private String email;

	@NotEmpty(message = "sex should not be empty")
	@Pattern(regexp = "male|female", message = "sex should match male or female")
	private String sex;
	
	@TelephoneNumberValidatorAnnotation(message = "phone not correct")
	private String telephone;
	
	@NotEmpty(message = "ownerThings not empty")
	@Valid
	private List<OwnerThing> ownerThings;
	
	@Data
	public static class OwnerThing {
		@NotEmpty(message = "id not empty for update",groups = {UpdateGroup.class})
		private String id;
		private String path;
	}
}
