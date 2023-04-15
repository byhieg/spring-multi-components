package com.byhieg.controller;

import com.byhieg.dto.ExampleEntity;
import com.byhieg.dto.group.InsertGroup;
import com.byhieg.dto.group.UpdateGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 * Created by byhieg on 2023/4/15.
 * Mail to byhieg@gmail.com
 */
@RestController
@RequestMapping("/")
@Validated
// 这里使用统一的异常处理。
public class ExampleController {

	
	@PostMapping("add")
	public String post(@Validated({InsertGroup.class, Default.class}) @RequestBody ExampleEntity exampleEntity) {
		return "post success";
	}

	@PostMapping("update")
	public String put(@Validated({UpdateGroup.class,Default.class}) @RequestBody ExampleEntity exampleEntity) {
		return "put success";
	}


	@GetMapping("get/{id}")
	public String get(@PathVariable("id") @Pattern(regexp = "([0-9a-fA-F]{8}(-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}?)",message = "id is not correct") String id,
					  @RequestParam("token") @NotEmpty(message = "token is empty") String token) {
		return "get success";
	}
}
