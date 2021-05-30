package org.sample.controller;

import org.sample.domain.entities.City;
import org.sample.jpa.repositories.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CityController {
    private final CityRepository repository;

    CityController(CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cities")
    public List<City> listAll(@RequestParam(defaultValue = "false", name = "show_country") boolean showCountry) {
        if (showCountry) {
            return repository.findAll();
        } else {
            return repository.findAllByNameNotNull();
        }
    }


    @PostMapping("/cities")
    public City create(@RequestBody @Valid City City) {
        return this.repository.save(City);
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
