package com.bootcamp.ms.bankAccount.service;

import com.bootcamp.ms.commons.entity.ProductBank;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductBankService {

    Flux<ProductBank> findAll();

    Mono<ProductBank> find(@PathVariable String id);
}
