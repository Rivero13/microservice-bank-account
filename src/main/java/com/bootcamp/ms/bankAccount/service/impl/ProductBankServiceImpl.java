package com.bootcamp.ms.bankAccount.service.impl;


import com.bootcamp.ms.bankAccount.ProductBankConfig;
import com.bootcamp.ms.bankAccount.service.ProductBankService;
import com.bootcamp.ms.commons.entity.ProductBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductBankServiceImpl implements ProductBankService {

    @Autowired
    private WebClient client;

    @Autowired
    private ProductBankConfig productBankConfig;

    @Override
    public Flux<ProductBank> findAll() {
        return client.get()
                .uri(productBankConfig.getUrl().concat("/all"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(ProductBank.class));
    }

    @Override
    public Mono<ProductBank> find(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return client.get()
                .uri(productBankConfig.getUrl().concat("/{id}"), params)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> response.bodyToMono(ProductBank.class));
    }
}
