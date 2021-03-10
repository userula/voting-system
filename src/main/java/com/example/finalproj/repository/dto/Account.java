package com.example.finalproj.repository.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String groupName;

    private int age;

    private String interest;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Vote> votes;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>();
        roles.add(getRole());
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", age=" + age +
                ", interest='" + interest + '\'' +
                '}';
    }
}
