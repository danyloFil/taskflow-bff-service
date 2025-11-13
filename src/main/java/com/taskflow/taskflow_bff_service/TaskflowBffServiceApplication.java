package com.taskflow.taskflow_bff_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskflowBffServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskflowBffServiceApplication.class, args);
	}

}
