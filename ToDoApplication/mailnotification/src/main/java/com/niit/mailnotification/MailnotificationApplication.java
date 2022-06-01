package com.niit.mailnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MailnotificationApplication {



	public static void main(String[] args) {

		SpringApplication.run(MailnotificationApplication.class, args);
	}

}
