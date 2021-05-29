package org.sample.controller;

import org.sample.domain.entities.User;
import org.sample.jpa.repositories.UserRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return repository.findAll();
    }

    @PostMapping("/users")
    public User create(@RequestBody @Validated User user) {
        return this.repository.save(user);
    }
}
