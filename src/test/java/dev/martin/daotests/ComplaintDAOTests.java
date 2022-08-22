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
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintDAOTests {

    static ComplaintDAO complaintDAO = new ComplaintDAOPostgres();

    @BeforeAll
    static public void setup() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create meeting table for use
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

            //create complaint table for use
            sql = "create table complaint(\n" +
                    "\tcomplaint_id serial primary key,\n" +
                    "\tdescription varchar(500) not null,\n" +
                    "\tmeeting int references meeting(meeting_id),\n" +
                    "\tstatus varchar(20)\n" +
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
    }

    @Test
    @Order(2)
    void update_complaint_test() {
        Complaint complaint2 = new Complaint(1, "The toilet gator has made its home in my tub",
                -1, Status.HIGHPRIORITY);
        complaintDAO.updateComplaint(complaint2);
        Complaint updatedComplaint = complaintDAO.getAllComplaints().get(0);
        Assertions.assertEquals(Status.HIGHPRIORITY, updatedComplaint.getStatus());
    }

    @Test
    @Order(3)
    void get_all_complaints_test() {
        complaintDAO.createComplaint(new Complaint(0, "My pet gator is missing!",
                0, Status.PENDING));
        List<Complaint> complaintList = complaintDAO.getAllComplaints();
        Assertions.assertEquals(2, complaintList.size());
    }

    @AfterAll
    static void teardown() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Drop complaint table first, then meeting table
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
