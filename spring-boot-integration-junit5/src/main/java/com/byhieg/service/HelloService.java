package com.byhieg.service;

import com.byhieg.component.HelloHelper;
import com.byhieg.utils.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@Service
public class HelloService {

	private final HelloHelper helloHelper;

	@Autowired
	public HelloService(HelloHelper helloHelper) {
		this.helloHelper = helloHelper;
	}
	
	private String helloPrefix = "hello";

	public String getHello(String content) {
		helloHelper.injectLog("getHello");
		return NameUtil.generate(helloPrefix, content);
	}
}
