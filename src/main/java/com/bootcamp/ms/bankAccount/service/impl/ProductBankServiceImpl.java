package com.bootcamp.ms.bankAccount.service.impl;

import com.bootcamp.ms.bankAccount.ProductBankConfig;
import com.bootcamp.ms.bankAccount.service.ProductBankService;
import com.bootcamp.ms.commons.entity.ProductBank;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProductBankServiceImpl implements ProductBankService {

    @Autowired
    private WebClient.Builder client;

    @Autowired
    private ProductBankConfig productBankConfig;

    private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    @Override
    public Flux<ProductBank> findAll() {
        System.out.println(" Making a request to " + productBankConfig.getUrl() + " at :" + LocalDateTime.now());
        return client.baseUrl(productBankConfig.getUrl())
                .build()
                .get()
                .uri("/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductBank.class)
                .transform(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customer-service");
                    return rcb.run(it, throwable -> fallbackProductBank());
                });

//        return client
//                //.baseUrl(productBankConfig.getUrl())
//                .uri(productBankConfig.getUrl().concat("/all"))
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .flatMapMany(response -> response.bodyToFlux(ProductBank.class));
    }

    @Override
    public Mono<ProductBank> find(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return client.baseUrl(productBankConfig.getUrl())
                .build()
                .get()
                .uri("/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductBank.class);
//        return client.get()
//                .uri(productBankConfig.getUrl().concat("/{id}"), params)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchangeToMono(response -> response.bodyToMono(ProductBank.class));
//    }

    }

    private Flux<ProductBank> fallbackProductBank() {
        // fetch results from the cache
        return Flux.just(new ProductBank("0","Error product bank"));
    }
}
