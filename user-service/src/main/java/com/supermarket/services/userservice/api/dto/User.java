package com.supermarket.services.userservice.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String userType;
}
