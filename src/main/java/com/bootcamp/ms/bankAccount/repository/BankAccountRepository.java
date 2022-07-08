package com.bootcamp.ms.bankAccount.repository;

import com.bootcamp.ms.commons.entity.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String> {
    Mono<BankAccount> findByTypeAndIdClient(String type, String idClient);

}
