package com.piesat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.piesat.mapper")
@SpringBootApplication
public class MavenPluginBuildDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavenPluginBuildDockerApplication.class, args);
	}

}
