package org.sample.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_company")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank(message = "Name is mandatory")
    public String name;
    @NotBlank(message = "Identification number is mandatory")
    public String identification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;
}
