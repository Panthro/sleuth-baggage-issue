package com.sleuth.issues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SleuthCaseIssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthCaseIssueApplication.class, args);
	}
}
