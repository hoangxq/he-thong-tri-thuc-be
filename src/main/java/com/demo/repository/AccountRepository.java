package com.demo.repository;

import com.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findOneByEmail(String email);

    Optional<Account> findOneByUsername(String username);

    Boolean existsByUsernameOrEmail(String username, String email);
}
