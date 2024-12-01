package com.autoria.autoriaplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Обозначаем поле как Primary Key

    private String name;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role; // связь с ролью

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType; // связь с типом аккаунта

    @OneToMany(mappedBy = "user")
    private Set<Advertisement> advertisements; // объявления, связанные с пользователем

    @ManyToOne
    private Permission permission;

    // Реализация методов UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Возвращаем роль как авторитет
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName())); // Предполагаем, что role.getName() возвращает строку с ролью (например, "ROLE_USER")
        return authorities;
    }

    @Override
    public String getUsername() {
        return email; // Используем email как уникальный идентификатор пользователя
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Предположим, что аккаунт никогда не истекает
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Предположим, что аккаунт не заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Предположим, что учетные данные никогда не истекают
    }

    @Override
    public boolean isEnabled() {
        return true; // Предположим, что аккаунт всегда активен
    }
}
