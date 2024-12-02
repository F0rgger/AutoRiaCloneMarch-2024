package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.model.AccountType;
import com.autoria.autoriaplatform.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-types")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @Autowired
    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }


    @GetMapping("/{name}")
    public AccountType getAccountTypeByName(@PathVariable String name) {
        return accountTypeService.getAccountTypeByName(name);
    }


    @PostMapping("/create")
    public AccountType createAccountType(@RequestBody AccountType accountType) {
        return accountTypeService.saveAccountType(accountType);
    }
}
