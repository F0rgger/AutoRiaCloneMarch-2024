package com.autoria.autoriaplatform.service;


import com.autoria.autoriaplatform.model.AccountType;
import com.autoria.autoriaplatform.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeService(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    public AccountType getAccountTypeByName(String name) {
        return accountTypeRepository.findByName(name);
    }

    public AccountType saveAccountType(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }
}
