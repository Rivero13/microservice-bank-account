package com.bootcamp.ms.bankAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.bootcamp.ms.commons.entity"})
public class MicroserviceBankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBankAccountApplication.class, args);
	}

}
