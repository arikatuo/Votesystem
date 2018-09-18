package com.hundsun.votesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hundsun.votesystem.mapper")
@ComponentScan(basePackages = "com.hundsun.votesystem")
public class VotesystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(VotesystemApplication.class, args);
	}
}
