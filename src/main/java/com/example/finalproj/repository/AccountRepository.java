package com.example.finalproj.repository;

import com.example.finalproj.repository.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    void deleteAccountByUserId(Long id);
}
