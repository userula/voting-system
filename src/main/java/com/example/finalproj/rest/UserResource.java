package com.example.finalproj.rest;

import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.rest.dto.LoginDto;
import com.example.finalproj.util.Converter;
import com.example.finalproj.rest.dto.UserDto;
import com.example.finalproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private UserService userService;
    private Converter converter;
    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
        this.converter = new Converter();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return converter.accountToUserDto(userService.getUser(id));
    }
    @GetMapping
    public List<Account> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public void login(LoginDto dto) {

    }
}
