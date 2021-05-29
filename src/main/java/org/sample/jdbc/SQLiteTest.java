package org.sample.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SQLiteTest {

    private static final Logger logger = LogManager.getLogger(SQLiteTest.class);

    public static void main(String[] args) throws SQLException {
        try (Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:sqlite:./test.db"
        )) {

            try (Statement cstmt = conn.createStatement())
            {

                cstmt.executeUpdate("DROP TABLE IF EXISTS person");
                cstmt.execute("CREATE TABLE IF NOT EXISTS person (id integer not null primary key, name varchar(20), address varchar(100), phone varchar(15), email varchar(15));");
                cstmt.executeUpdate("insert into person (id, name, address, phone, email) values (1, 'Sam Jones', '12 Letsby Avenue, SN7 2QN, UK', '07700-900411', 'samjones49@mymail.not.fr');");
                cstmt.executeUpdate("insert into person (id, name, address, phone, email) values (2, 'Bibi Cozby', '05 Letsby Avenue, SN7 2QN, UK', '18909-900411', 'bibi@mymail.not.fr');");

                ResultSet rs = cstmt.executeQuery("SELECT * FROM person WHERE name like '%Sam%';");
                System.out.println("Found these people with name like 'Sam': ");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Telephone: " + rs.getString("phone"));
                    System.out.println("Address: " + rs.getString("address"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("");
                }

                PreparedStatement ps = conn.prepareStatement("select name, count(*) from person where phone like ? group by name");
                ps.setString(1, "%189%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString(1) + ": " + rs.getString(2));
                }

            }
            catch (SQLException sqle) {
                logger.error(sqle.getMessage() + " attempting to execute query on connection");
            }
        }

    }
}
