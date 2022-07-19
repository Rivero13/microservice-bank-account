package com.bootcamp.ms.bankAccount;

import com.bootcamp.ms.bankAccount.config.CircuitBreakerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.bootcamp.ms.commons.entity"})
@Import({CircuitBreakerConfiguration.class})
public class MicroserviceBankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBankAccountApplication.class, args);
	}

}
