package dev.martin.daotests;

import dev.martin.data.MeetingDAO;
import dev.martin.data.MeetingDAOPostgres;
import dev.martin.entities.Meeting;
import dev.martin.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingDAOTests {

    static MeetingDAO meetingDAO = new MeetingDAOPostgres();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void create_meeting_test() {
        Meeting meeting = new Meeting(0,"Gator talk", "My house", 500);
        Meeting savedMeeting = meetingDAO.createMeeting(meeting);
        Assertions.assertEquals(1, savedMeeting.getMeetingId());
    }

    @Test
    @Order(2)
    public void get_all_meetings_test() {
        meetingDAO.createMeeting(new Meeting(0, "Gators can talk?", "The sewers", 1800));
        Assertions.assertEquals(2, this.meetingDAO.getAllMeetings().size());
    }

    @AfterAll
    public static void teardown() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            String sql = "drop table meeting";
            Statement statement = conn.createStatement();
            statement.execute(sql);

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
