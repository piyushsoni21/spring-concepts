package com.spring.concept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Piyush Soni
 *
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)  
public class SpringConceptApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConceptApplication.class, args);
	}

}
