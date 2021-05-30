package org.sample.controller;

import org.sample.domain.entities.Company;
import org.sample.jpa.repositories.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {
    private final CompanyRepository repository;

    CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/companies")
    public List<Company> findAll() {
        return repository.findAll();
    }

    @PostMapping("/companies")
    public Company create(@RequestBody @Valid Company company) {
        return this.repository.save(company);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
