package org.sample.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.domain.entities.Person;

import java.sql.*;

public class PersonManualDbPersister {

    private static final Logger logger = LogManager.getLogger(SQLiteTest.class);

    private Connection conn;

    public PersonManualDbPersister(String url) throws SQLException {
        conn = DriverManager.getConnection(url);
    }

    public void createPerson(Person person)
    {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into person (name, address, phone) values (?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, person.name);
            stmt.setString(2, person.address);
            stmt.setString(3, person.phone);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                person.id = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
        }
    }
}
