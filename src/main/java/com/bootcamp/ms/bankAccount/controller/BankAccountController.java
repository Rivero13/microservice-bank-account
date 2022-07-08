package com.bootcamp.ms.bankAccount.controller;

import com.bootcamp.ms.bankAccount.service.BankAccountService;
import com.bootcamp.ms.bankAccount.service.ClientService;
import com.bootcamp.ms.bankAccount.service.ProductBankService;
import com.bootcamp.ms.commons.entity.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private ProductBankService productBankService;

    @Autowired
    private ClientService clientService;

    private final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    @GetMapping("/all")
    public Flux<BankAccount> getAll(){
        return bankAccountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<BankAccount> find(@PathVariable String id){
        return bankAccountService.findById(id);
    }

    @GetMapping("/{type}/{clientId}")
    public Mono<BankAccount> prueba(@PathVariable String type, @PathVariable String clientId){
        return bankAccountService.searchBankAccountByTypeAndIdClient(type, clientId);
    }

    @PostMapping("/create")
    public Mono<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount){
        productBankService.find(bankAccount.getIdProduct())
                        .flatMap(p -> {
                            logger.info(p.getDescription());
                            bankAccount.setProductBank(p);
                            return Mono.empty();
                        }).subscribe();

        return clientService.find(bankAccount.getIdClient())
                .flatMap(c -> {
                    bankAccount.setClient(c);
                    bankAccount.maintenanceFee(bankAccount.getType());
                    logger.info(c.getFirstName());

                    if(c.getType().equalsIgnoreCase("personal")){
                        if(bankAccount.getType().equalsIgnoreCase("ahorro") || bankAccount.getType().equalsIgnoreCase("cuenta corriente")){
                            logger.info("estoy en el segundo if");
                            return bankAccountService.save(bankAccount);
                        }
                    }else{
                        if(bankAccount.getType().equalsIgnoreCase("cuentas corrientes")){
                            return bankAccountService.save(bankAccount);
                        }
                    }
                    return Mono.empty();
                });
    }

    @PutMapping("/{id}")
    public Mono<BankAccount> updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount){

        logger.info("Editando la cuenta bancaria con el id: " + id);

        return bankAccountService.findById(id)
                .flatMap(b -> {
                    b.setType(bankAccount.getType());
                    b.setAmount(bankAccount.getAmount());
                    b.setIdProduct(bankAccount.getIdProduct());
                    b.setIdClient(bankAccount.getIdClient());

                    return bankAccountService.save(b);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBankAccount(@PathVariable String id) {
        return bankAccountService.findById(id).flatMap(b -> {
            return bankAccountService.delete(b);
        });
    }

    @PutMapping(value = "/deposit/{id}/{amount}")
    public Mono<Double> deposit(@PathVariable String id, @PathVariable double amount){
        bankAccountService.findById(id)
                .flatMap(b -> {
                    Double currentAmount = b.getAmount() + amount;

                    b.setAmount(currentAmount);

                    return bankAccountService.save(b);
                });

        return bankAccountService.checkBalance(id);

    }

    @PutMapping(value = "/withdraw/{id}/{amount}")
    public Mono<Double> withdraw(@PathVariable String id, @PathVariable double amount){

        bankAccountService.findById(id)
                .flatMap(b -> {
                  if(b.getAmount() > amount){
                      Double currentAmount = b.getAmount() - amount;

                      b.setAmount(currentAmount);

                      return bankAccountService.save(b);
                  }

                  return Mono.empty();
                });

        return bankAccountService.checkBalance(id);
    }

    @GetMapping(value = "/checkBalance/{id}")
    public Mono<Double> checkBalance(@PathVariable String id){
        return bankAccountService.checkBalance(id);
    }

}
