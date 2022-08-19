package dev.martin.data;

import dev.martin.entities.Meeting;

import java.util.List;

public interface MeetingDAO {

    //Create
    Meeting createMeeting(Meeting meeting);

    //Read
    List<Meeting> getAllMeetings();

}
