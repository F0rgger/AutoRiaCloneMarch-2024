package com.autoria.autoriaplatform.repository;


import com.autoria.autoriaplatform.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    // Найти тип аккаунта по имени
    AccountType findByName(String name);
}
