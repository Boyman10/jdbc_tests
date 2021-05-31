package org.sample.jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sample.domain.entities.Country;
import org.sample.jpa.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CountryRepositoryIntegrationTest {

    @Autowired
    CountryRepository countryRepository;

    @BeforeAll
    static void setup(@Autowired DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("data-init.sql"));
        }
    }

    @Test
    void whenFindById_thenReturnCountry() {

        // when
        Optional<Country> optionalCountry = countryRepository.findById(1L);
        Assertions.assertTrue(optionalCountry.isPresent());
        Country found = optionalCountry.get();
        // then
        Assertions.assertEquals(found.name, "Spain");
    }
}
