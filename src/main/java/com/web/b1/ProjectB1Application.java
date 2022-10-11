package com.web.b1;

import com.web.b1.config.BatchConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.web")
@EnableBatchProcessing
@EnableCaching
public class ProjectB1Application {
	static final Logger log = LoggerFactory.getLogger(ProjectB1Application.class);
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProjectB1Application.class, args);
		int exitCode = SpringApplication.exit(context);
		System.exit(exitCode);
	}

}
/*
(scanBasePackages = "com.web.b1")
 */