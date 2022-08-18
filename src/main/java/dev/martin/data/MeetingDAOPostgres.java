package dev.martin.data;

import dev.martin.entities.Meeting;
import dev.martin.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAOPostgres implements MeetingDAO {

    //Read
    @Override
    public List<Meeting> getAllMeetings() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            String sql = "select * from meeting";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Meeting> meetingList = new ArrayList();

            while (resultSet.next()) {
                Meeting meeting = new Meeting();
                meeting.setMeetingId(resultSet.getInt("meeting_id"));
                meeting.setDescription(resultSet.getString("description"));
                meeting.setPlace(resultSet.getString("place"));
                meeting.setTime(resultSet.getInt("time"));
                meetingList.add(meeting);
            }

            meetingList.removeIf(meeting -> meeting.getMeetingId() == -1);

            return meetingList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
