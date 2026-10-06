package com.doducvinh.base;

import org.springframework.boot.SpringApplication;

public class TestBaseApplication {

	public static void main(String[] args) {
		SpringApplication.from(BaseApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
