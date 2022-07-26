package com.bootcamp.ms.bankAccount.service;

import com.bootcamp.ms.commons.entity.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> getAll();

    Mono<Client> findById(String id);
}
