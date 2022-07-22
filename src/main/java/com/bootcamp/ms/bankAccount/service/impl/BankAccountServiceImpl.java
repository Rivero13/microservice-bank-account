package com.bootcamp.ms.bankAccount.service.impl;

import com.bootcamp.ms.bankAccount.repository.BankAccountRepository;
import com.bootcamp.ms.bankAccount.service.BankAccountService;
import com.bootcamp.ms.commons.entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Mono<BankAccount> searchBankAccountByTypeAndIdClient(String type, String idClient) {
        return bankAccountRepository.findByTypeAndIdClient(type, idClient);
    }

    @Override
    public Flux<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Mono<BankAccount> findById(String id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public Mono<BankAccount> save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public Mono<Void> delete(BankAccount bankAccount) {
        return bankAccountRepository.delete(bankAccount);
    }

    @Override
    public Mono<Double> checkBalance(String id) {
        return bankAccountRepository.findById(id).map(BankAccount::getAmount);
    }

    @Override
    public Mono<Integer> consultMovements(String id) {
        return bankAccountRepository.findById(id).map(BankAccount::getMaxMovement);
    }

    @Override
    public Optional<BankAccount> findByIdClient(String id) {
        return bankAccountRepository.findAll()
                .toStream()
                .filter(b -> b.getIdClient().contains(id))
                .findFirst();
    }
}
