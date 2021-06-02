package com.bpcl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@MapperScan(basePackages = "com.bpcl.mapper")
public class PailPrintingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PailPrintingApplication.class, args);
	}

}
