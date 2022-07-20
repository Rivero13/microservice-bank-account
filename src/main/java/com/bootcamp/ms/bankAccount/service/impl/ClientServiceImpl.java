package com.bootcamp.ms.bankAccount.service.impl;

import com.bootcamp.ms.bankAccount.ClientConfig;
import com.bootcamp.ms.bankAccount.service.ClientService;
import com.bootcamp.ms.commons.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private WebClient.Builder client;

    @Autowired
    private ClientConfig clientConfig;

    @Override
    public Flux<Client> getAll() {
        return client.baseUrl(clientConfig.getUrl())
                .build()
                .get()
                .uri("/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Client.class);

//        return client.get().uri(clientConfig.getUrl().concat("/all"))
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .flatMapMany(response -> response.bodyToFlux(Client.class));
    }

    @Override
    public Mono<Client> find(String id) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return client.baseUrl(clientConfig.getUrl())
                .build()
                .get()
                .uri("/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Client.class);

//        return client.get()
//                .uri(clientConfig.getUrl().concat("/{id}"), params)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchangeToMono(response -> response.bodyToMono(Client.class));
    }
}
