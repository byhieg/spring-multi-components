package com.byhieg.controller;

import com.byhieg.entity.WebResponse;
import com.byhieg.service.HelloService;
import com.byhieg.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@RestController
@RequestMapping("hello")
public class HelloWorldController {

	
	private HelloService helloService;

	@Autowired
	public HelloWorldController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/get")
	public WebResponse<String> get(@RequestParam("content")String content) {
		return ResponseUtil.OK(helloService.getHello(content));
	}
}
