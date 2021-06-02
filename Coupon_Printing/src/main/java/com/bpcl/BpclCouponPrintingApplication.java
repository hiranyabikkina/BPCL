package com.bpcl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.bpcl.mapper")
public class BpclCouponPrintingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BpclCouponPrintingApplication.class, args);
	}

}
