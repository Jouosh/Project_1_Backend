package dev.martin.service;

import dev.martin.entities.Meeting;

import java.util.List;

public interface MeetingService {

    //Create
    Meeting registerMeeting(Meeting meeting);

    //Read
    List<Meeting> retrieveAllMeetings();
}
