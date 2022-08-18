package dev.martin.data;

import dev.martin.entities.Meeting;

import java.util.List;

public interface MeetingDAO {

    //Read
    List<Meeting> getAllMeetings();

}
