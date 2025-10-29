package com.example.demo_test_mongodb;

import org.springframework.boot.SpringApplication;

public class TestDemoTestMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoTestMongodbApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
