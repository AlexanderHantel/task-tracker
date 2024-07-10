package com.hantel.tasktracker.accountservice.controller;

import com.hantel.tasktracker.accountservice.entity.Account;
import com.hantel.tasktracker.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/getAll")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID id) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(account);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID id, @RequestBody Account account) {
        Account persistedAccount = accountRepository.findById(id).orElse(null);

        if (persistedAccount == null) {
            return ResponseEntity.notFound().build();
        }

        persistedAccount.setUserName(account.getUserName());
        persistedAccount.setEmail(account.getEmail());

        Account updatedAccount = accountRepository.save(persistedAccount);

        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        accountRepository.delete(account);

        return ResponseEntity.noContent().build();
    }
}
