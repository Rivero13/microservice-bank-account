package com.bootcamp.ms.bankAccount.service;

import com.bootcamp.ms.commons.entity.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface BankAccountService {

    Mono<BankAccount> searchBankAccountByTypeAndIdClient(String type, String idClient);
    Flux<BankAccount> findAll();
    Mono<BankAccount> findById(String id);
    Mono<BankAccount> save(BankAccount bankAccount);
    Mono<Void> delete(BankAccount bankAccount);
    Mono<Double> checkBalance(String id);
    Mono<Integer> consultMovements(String id);
    Optional<BankAccount> findByIdClient(String id);
}
