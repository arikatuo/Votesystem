package com.hundsun.votesystem.util;


import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@Configuration
@ComponentScan("com.hundsun.votesystem.service.impl")
@EnableAsync  // 启用异步任务
public class ThreadConfig {
	
	// 这里是声明一个bean，类似于xml中的<bean>标签。
    // Executor 就是一个线程池
    @Bean
    public Executor getExecutor() {
         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
         executor.setCorePoolSize(10);
         executor.setMaxPoolSize(100);
         executor.setQueueCapacity(50);
         executor.initialize();
         return executor;
    }


}
