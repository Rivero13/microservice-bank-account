package com.bootcamp.ms.bankAccount.controller;

import com.bootcamp.ms.commons.entity.BankAccount;
import com.bootcamp.ms.commons.entity.Client;
import com.bootcamp.ms.commons.entity.ProductBank;
import reactor.core.publisher.Flux;

import java.util.Date;

public class Datos {

    public final static ProductBank productBank = ProductBank.builder()
            .id("123")
            .description("Cuentas Corrientes")
            .build();

    public final static ProductBank productBank2 = ProductBank.builder()
            .id("456")
            .description("Creditos")
            .build();

    public final static Client client = Client.builder()
            .id("258")
            .firstName("Jair")
            .lastName("Rivero")
            .documentNumber("70976181")
            .age("23")
            .type("Personal")
            .build();

    public final static Client client2 = Client.builder()
            .id("147")
            .firstName("Pedro")
            .lastName("Suarez")
            .documentNumber("45671124")
            .age("25")
            .type("Empresarial")
            .build();

    public final static Flux<BankAccount> BANKACCOUNT = Flux.just(
            new BankAccount("1234567890", "Ahorro", 0.0, 5, new Date(), 1200.00, "123", "258", client, productBank),
            new BankAccount("1472583690", "Cuenta corriente", 2.50, 100, new Date(), 850.00, "456", "147", client2, productBank2)
    );
}
