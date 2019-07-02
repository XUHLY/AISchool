package cn.org.aischool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 * 启动类
 * @author haungliyang
 * 
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@MapperScan("cn.org.aischool.dao")
public class AISchoolApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(AISchoolApplication.class,args);
		
	}

	
	
}
