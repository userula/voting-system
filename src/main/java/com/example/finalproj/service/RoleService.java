package com.example.finalproj.service;

import com.example.finalproj.repository.AccountRepository;
import com.example.finalproj.repository.RoleRepository;
import com.example.finalproj.repository.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRole(Long id) {
        return roleRepository.getOne(id);
    }

}
