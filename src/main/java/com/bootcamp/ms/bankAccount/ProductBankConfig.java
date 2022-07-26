package com.bootcamp.ms.bankAccount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBankConfig {

    @Value("${endpoint.productBank}")
    private String url;

    public String getUrl() {
        return url;
    }
}
