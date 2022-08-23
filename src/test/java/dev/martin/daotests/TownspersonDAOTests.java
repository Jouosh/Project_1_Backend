package dev.martin.daotests;

import dev.martin.data.TownspersonDAO;
import dev.martin.data.TownspersonDAOPostrgres;
import dev.martin.entities.Townsperson;
import dev.martin.utils.ConnectionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TownspersonDAOTests {

    static TownspersonDAO townspersonDAO = new TownspersonDAOPostrgres();

    @BeforeAll
    static void setup() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create meeting table for use
            String sql = "create table townsperson(\n" +
                    "town_id serial primary key,\n" +
                    "username varchar(20) unique,\n" +
                    "password varchar(20),\n" +
                    "role varchar(20)\n" +
                    ");";

            //create a statement, and have it execute the sql string
            Statement statement = conn.createStatement();
            statement.execute(sql);

            //insert default townsperson
            sql = "insert into townsperson values (default, 'Jooosh','fakePassword123', 'COUNCIL');";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void get_townsperson_by_username_test() {
        Townsperson townsperson = townspersonDAO.getTownspersonByUsername("Jooosh");
        Assertions.assertEquals("fakePassword123", townsperson.getPassword());
    }

    @AfterAll
    static void teardown() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Drop complaint table first, then meeting table
            String sql = "drop table townsperson";
            Statement statement = conn.createStatement();
            statement.execute(sql);

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
