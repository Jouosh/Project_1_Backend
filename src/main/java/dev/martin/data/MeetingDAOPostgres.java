package dev.martin.data;

import dev.martin.entities.Meeting;
import dev.martin.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAOPostgres implements MeetingDAO {

    //Create
    @Override
    public Meeting createMeeting(Meeting meeting) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create prepared statement and fill in blanks
            String sql = "insert into meeting values (default, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,meeting.getDescription());
            preparedStatement.setString(2, meeting.getPlace());
            preparedStatement.setLong(3, meeting.getTime());

            //Execute and get generated id
            preparedStatement.execute();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            int generatedKey = keys.getInt("meeting_id");

            //Set meetingId and return meeting
            meeting.setMeetingId(generatedKey);
            return meeting;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Read
    @Override
    public List<Meeting> getAllMeetings() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create select statement, store results in result set
            String sql = "select * from meeting";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            //Create list to hold meetings
            List<Meeting> meetingList = new ArrayList();

            //create a temp meeting to store data, add onto meetingList until resultSet is iterated through
            while (resultSet.next()) {
                Meeting meeting = new Meeting();
                meeting.setMeetingId(resultSet.getInt("meeting_id"));
                meeting.setDescription(resultSet.getString("description"));
                meeting.setPlace(resultSet.getString("place"));
                meeting.setTime(resultSet.getLong("time"));
                meetingList.add(meeting);
            }

            //remove fake meeting from the list and return
            meetingList.removeIf(meeting -> meeting.getMeetingId() == -1);

            return meetingList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
