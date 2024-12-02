package com.autoria.autoriaplatform.Initializer;

import com.autoria.autoriaplatform.enums.ERole;
import com.autoria.autoriaplatform.model.Role;
import com.autoria.autoriaplatform.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_BUYER));
            roleRepository.save(new Role(ERole.ROLE_SELLER));
            roleRepository.save(new Role(ERole.ROLE_MANAGER));
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            roleRepository.save(new Role(ERole.ROLE_USER));
        }
    }
}