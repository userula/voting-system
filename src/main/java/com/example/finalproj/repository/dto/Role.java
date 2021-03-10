package com.example.finalproj.repository.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<Account> accounts;

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
