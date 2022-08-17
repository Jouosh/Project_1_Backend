package dev.martin.daotests;

import dev.martin.data.ComplaintDAO;
import dev.martin.data.ComplaintDAOPostgres;
import dev.martin.entities.Complaint;
import dev.martin.entities.Status;
import dev.martin.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintDAOTests {

    static ComplaintDAO complaintDAO = new ComplaintDAOPostgres();

    @BeforeAll
    static public void setup() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create a test only version of meeting for use
            String sql = "create table meeting(\n" +
                    "\tmeeting_id serial primary key,\n" +
                    "\tdescription varchar(500),\n" +
                    "\tplace varchar(100),\n" +
                    "\ttime int\n" +
                    ");";

            //create a statement, and have it execute the sql string
            Statement statement = conn.createStatement();
            statement.execute(sql);

            //insert default fake meeting into meeting_test
            sql = "insert into meeting values (-1, 'No meeting assigned', 'This is fake', 0);";
            statement.execute(sql);

            //create a test only version of complaint table for use
            sql = "create table complaint(\n" +
                    "\tcomplaint_id serial primary key,\n" +
                    "\tdescription varchar(500) not null,\n" +
                    "\tmeeting int references meeting(meeting_id),\n" +
                    "\tstatus varchar(10)\n" +
                    ");";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_complaint_test() {
        Complaint complaint = new Complaint(0, "A sewer gator came out of my toilet",
                0, Status.PENDING);
        Complaint savedComplaint = complaintDAO.createComplaint(complaint);
        Assertions.assertNotEquals(0, savedComplaint.getComplaintId());
    };

    @AfterAll
    static void teardown() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Drop complaint test table first, then meeting test table
            String sql = "drop table complaint";
            Statement statement = conn.createStatement();
            statement.execute(sql);

            sql = "drop table meeting";
            statement.execute(sql);

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}