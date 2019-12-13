package com.piesat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.piesat.entity.Emp;
import com.piesat.service.IEmpService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MavenPluginBuildDockerApplicationTests {

	@Autowired
	private IEmpService empService;
	
	@Test
	public void contextLoads() {
		Emp emp = empService.getById(1001);
		System.out.println(emp);
	}

}
