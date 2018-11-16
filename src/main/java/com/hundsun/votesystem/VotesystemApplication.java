package com.hundsun.votesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.hundsun.votesystem.util.ApplicationStartup;

@SpringBootApplication
@MapperScan(basePackages = "com.hundsun.votesystem.mapper")
@ComponentScan(basePackages = "com.hundsun.votesystem")
public class VotesystemApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VotesystemApplication.class);
	}

	public static void main(String[] args) {

		//SpringApplication.run(VotesystemApplication.class, args);
		SpringApplication springApplication = new SpringApplication(VotesystemApplication.class);
		springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
	}
}
