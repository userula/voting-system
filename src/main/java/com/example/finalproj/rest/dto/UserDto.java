package com.example.finalproj.rest.dto;

import lombok.Data;

@Data
public class UserDto {
    private long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String groupName;
    private int age;
    private String interest;
    private Long roleId;
}
