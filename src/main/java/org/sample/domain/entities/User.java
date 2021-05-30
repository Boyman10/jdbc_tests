package org.sample.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank(message = "FirstName is mandatory")
    public String firstName;
    @NotBlank(message = "LastName is mandatory")
    public String lastName;
}
