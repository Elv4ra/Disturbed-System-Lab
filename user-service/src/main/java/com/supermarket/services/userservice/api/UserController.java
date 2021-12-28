package com.supermarket.services.userservice.api;


import com.supermarket.services.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.supermarket.services.userservice.repository.model.User;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        try {
            final User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userType}")
    public ResponseEntity<List<User>> findAllUserByType(@PathVariable String userType) {
        List<User> users = userService.getAllUserByType(userType);
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.supermarket.services.userservice.api.dto.User user) {
        final String email = user.getEmail();
        final String password = user.getPassword();
        final String firstName = user.getFirstName();
        final String lastName = user.getLastName();
        final String userType = user.getUserType();
        try {
            final long id = userService.createUser(email, password, firstName, lastName, userType);
            final String location = String.format("/users/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.supermarket.services.userservice.api.dto.User user) {
        final String email = user.getEmail();
        final String password = user.getPassword();
        final String firstName = user.getFirstName();
        final String lastName = user.getLastName();
        final String userType = user.getUserType();
        try {
            userService.updateUser(id, email, password, firstName, lastName, userType);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
