package org.sample.jpa.repositories;

import org.sample.domain.entities.Country;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @EntityGraph(attributePaths = {"Country.cities"})
    @Override
    List<Country> findAll();
}
