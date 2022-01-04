package com.supermarket.services.userservice.repository.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String userType;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, String userType) {
        this.email = email;
        this.userPassword = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }
}
