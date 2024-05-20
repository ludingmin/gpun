package com.gpnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.gpnu.mapper")
@SpringBootApplication
public class ZzjpApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZzjpApplication.class, args);
	}

}
