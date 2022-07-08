package com.bootcamp.ms.bankAccount;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ProductBankConfig {

    @Value("${endpoint.productBank}")
    private String url;

}
