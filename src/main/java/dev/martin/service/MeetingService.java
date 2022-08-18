package dev.martin.service;

import dev.martin.entities.Meeting;

import java.util.List;

public interface MeetingService {

    //Read
    List<Meeting> retrieveAllMeetings();
}
