package org.sample.domain.entities;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    public Long id;
    @NonNull
    public String firstName;
    @NonNull
    public String lastName;
}
