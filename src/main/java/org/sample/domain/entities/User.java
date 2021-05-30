package org.sample.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "app_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank(message = "FirstName is mandatory")
    public String firstName;
    @NotBlank(message = "LastName is mandatory")
    public String lastName;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public Set<Company> companies;
}
