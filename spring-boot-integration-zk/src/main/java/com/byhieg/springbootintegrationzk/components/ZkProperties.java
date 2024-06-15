package com.byhieg.springbootintegrationzk.components;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by byhieg on 2024/6/12.
 * Mail to byhieg@gmail.com
 */
@ConfigurationProperties(value="spring.zk")
@Data
public class ZkProperties {
	
	private String zkServers;
	private Integer sessionTimeout;
	private Integer connectionTimeout;
	private Integer maxRetries;
	private Integer maxSleepTime;
	private String namespace;
}
