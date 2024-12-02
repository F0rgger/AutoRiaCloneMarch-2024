package com.autoria.autoriaplatform.repository;


import com.autoria.autoriaplatform.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    AccountType findByName(String name);
}
