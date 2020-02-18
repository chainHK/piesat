package com.piesat;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan(basePackages = "com.piesat.mapper")
@SpringBootApplication
public class PiesatProjectApplication {
	public static void main(String[] args) {

		log.info("ELK系统测试。。。。。。。。。。。。。。。。。。。。。。");

		SpringApplication.run(PiesatProjectApplication.class, args);
	}

}
