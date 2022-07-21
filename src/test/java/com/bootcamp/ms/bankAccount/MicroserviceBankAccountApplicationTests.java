package com.bootcamp.ms.bankAccount;

import com.bootcamp.ms.commons.entity.BankAccount;
import com.bootcamp.ms.commons.entity.Client;
import com.bootcamp.ms.commons.entity.ProductBank;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class MicroserviceBankAccountApplicationTests {

	private final ProductBank productBank = ProductBank.builder()
			.id("12345")
			.description("cuentas corrientes")
			.build();

	private final Client client = Client.builder()
			.id("1234567")
			.firstName("Jair")
			.lastName("Rivero")
			.age("23")
			.documentNumber("70976181")
			.type("Personal")
			.build();

	private final BankAccount bankAccount = BankAccount.builder()
			.id("1234567890")
			.type("Ahorro")
			.maintenanceFee(2.50)
			.maxMovement(5)
			.date(new Date())
			.amount(1200.00)
			.idProduct("12345")
			.idClient("1234567")
			.productBank(productBank)
			.client(client)
			.build();

	@Test
	void create() {
		assertAll(
				() -> assertNotNull(bankAccount)
		);
	}

	@Test
	void testNotNull() {
		assertAll(
				() -> assertNotNull(bankAccount.getType()),
				() -> assertNotNull(bankAccount.getMaintenanceFee()),
				() -> assertNotNull(bankAccount.getMaxMovement()),
				() -> assertNotNull(bankAccount.getDate()),
				() -> assertNotNull(bankAccount.getAmount()),
				() -> assertNotNull(bankAccount.getIdProduct()),
				() -> assertNotNull(bankAccount.getIdClient()),
				() -> assertNotNull(bankAccount.getProductBank()),
				() -> assertNotNull(bankAccount.getClient())
		);
	}

	@Test
	void findById() {
		assertAll(
				() -> assertNotNull(bankAccount.getId()),
				() -> assertEquals("1234567890", bankAccount.getId())
		);
	}

	@Test
	void findByClient() {
		assertAll(
				() -> assertNotNull(bankAccount.getIdClient()),
				() -> assertNotNull(bankAccount.getClient()),
				() -> assertEquals("1234567", bankAccount.getClient().getId())
		);
	}

	@Test
	void findByProductBank() {
		assertAll(
				() -> assertNotNull(bankAccount.getIdProduct()),
				() -> assertNotNull(bankAccount.getProductBank()),
				() -> assertEquals("12345", bankAccount.getProductBank().getId())
		);
	}
}
