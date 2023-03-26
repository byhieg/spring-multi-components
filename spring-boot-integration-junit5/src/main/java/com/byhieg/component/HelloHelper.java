package com.byhieg.component;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
@Component
public class HelloHelper {
	private Logger log = LoggerFactory.getLogger(HelloHelper.class);

	public void injectLog(String method) {
		log.info("hello method [{}] is called", method);
	}
}
