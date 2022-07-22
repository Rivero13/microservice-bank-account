package com.bootcamp.ms.bankAccount.controller;

import com.bootcamp.ms.bankAccount.repository.BankAccountRepository;
import com.bootcamp.ms.bankAccount.service.BankAccountService;
import com.bootcamp.ms.bankAccount.service.impl.BankAccountServiceImpl;
import com.bootcamp.ms.commons.entity.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountControllerTest {

    BankAccountRepository bankAccountRepository;
    BankAccountService bankAccountService;

    @BeforeEach
    void setUp() {
        bankAccountRepository = Mockito.mock(BankAccountRepository.class);
        bankAccountService = new BankAccountServiceImpl(bankAccountRepository);
    }

    @Test
    void findByIdClient() {
        Mockito.when(bankAccountRepository.findAll()).thenReturn(Datos.BANKACCOUNT);
        Optional<BankAccount> bankAccount = bankAccountService.findByIdClient("258");

        assertAll(
                () -> assertTrue(bankAccount.isPresent()),
                () -> assertTrue(bankAccount.orElseThrow().getAmount() > 500),
                () -> assertEquals("Ahorro", bankAccount.orElseThrow().getType()),
                () -> assertEquals("Cuentas Corrientes", bankAccount.orElseThrow().getProductBank().getDescription()),
                () -> assertEquals("Jair", bankAccount.orElseThrow().getClient().getFirstName()),
                () -> assertEquals("Rivero", bankAccount.orElseThrow().getClient().getLastName())
        );
    }
}