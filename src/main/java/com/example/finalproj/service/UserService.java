package com.example.finalproj.service;

import com.example.finalproj.config.SpringSecurityConfig;
import com.example.finalproj.repository.AccountRepository;
import com.example.finalproj.repository.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(AccountRepository accountRepository, PasswordEncoder securityConfig) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = securityConfig;
    }

    public void registerUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public Account getUser(Long id) {
        return accountRepository.getOne(id);
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }
    public void updateUser(Long id, Account user){
        Account updatedUser = accountRepository.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setGroupName(user.getGroupName());
        updatedUser.setInterest(user.getInterest());
        if (user.getRole() != null){
            updatedUser.setRole(user.getRole());
        }
        accountRepository.save(updatedUser);
    }

    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    @Transactional
    public void deleteUser(Long id){
        accountRepository.deleteAccountByUserId(id);
    }
}
