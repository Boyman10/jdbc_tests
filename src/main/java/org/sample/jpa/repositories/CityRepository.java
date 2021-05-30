package org.sample.jpa.repositories;

import org.sample.domain.entities.City;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @EntityGraph(value = "city-entity-graph")
    @Override
    List<City> findAll();
}
