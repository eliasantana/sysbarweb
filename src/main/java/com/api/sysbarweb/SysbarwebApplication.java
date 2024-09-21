package com.api.sysbarweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Configuration
@Component
public class SysbarwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysbarwebApplication.class, args);
	}

}
