package com.byhieg.springbootintegrationzk;

import com.byhieg.springbootintegrationzk.components.ZkProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ZkProperties.class)
public class SpringBootIntegrationZkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntegrationZkApplication.class, args);
    }

}
