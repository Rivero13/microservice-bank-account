package com.bootcamp.ms.bankAccount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Value("${endpoint.client}")
    private String url;

    public String getUrl() {
        return url;
    }
}
