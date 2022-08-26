package dev.martin.daotests;

import dev.martin.data.TownspersonDAO;
import dev.martin.data.TownspersonDAOPostrgres;
import dev.martin.entities.Role;
import dev.martin.entities.Townsperson;
import dev.martin.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
                    "role varchar(20),\n" +
                    "approved boolean\n" +
                    ");";

            //create a statement, and have it execute the sql string
            Statement statement = conn.createStatement();
            statement.execute(sql);

            //insert default townsperson
            sql = "insert into townsperson values (default, 'Jooosh','fakePassword123', 'COUNCIL', true);";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_townsperson_test() {
        Townsperson townsperson = new Townsperson(1, "Jimber", "passy",
                Role.CONSTITUENT, false);
        Townsperson savedTownsperson = townspersonDAO.createTownsperson(townsperson);
        Assertions.assertNotEquals(1, savedTownsperson.getTownId());
    }

    @Test
    @Order(2)
    void get_townsperson_by_username_test() {
        Townsperson townsperson = townspersonDAO.getTownspersonByUsername("Jooosh");
        Assertions.assertEquals("fakePassword123", townsperson.getPassword());
    }

    @Test
    @Order(3)
    void get_townspersons_by_approval_test() {
        List<Townsperson> townspeople = townspersonDAO.getTownspeopleByApproval(true);
        Assertions.assertEquals(1, townspeople.size());
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
