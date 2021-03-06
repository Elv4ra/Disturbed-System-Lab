package com.supermarket.services.userservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class User {
    private String email;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String userType;
}
