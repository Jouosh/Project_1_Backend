package dev.martin.service;

import dev.martin.data.MeetingDAO;
import dev.martin.entities.Meeting;

import java.util.List;

public class MeetingServiceImpl implements MeetingService{

    //Connection to data layer
    private MeetingDAO meetingDAO;

    //Constructor
    public MeetingServiceImpl(MeetingDAO meetingDAO) { this.meetingDAO = meetingDAO; }

    @Override
    public Meeting registerMeeting(Meeting meeting) {

        //Check that there is a description
        if (meeting.getDescription().length() == 0) {
            throw new RuntimeException("Meetings must have a description");
        }

        //Check that there is a place
        if (meeting.getPlace().length() == 0) {
            throw new RuntimeException("Meetings must have a place");
        }

        //Send to data layer if passed and return result
        Meeting savedMeeting = meetingDAO.createMeeting(meeting);

        return savedMeeting;

    }

    //Read
    @Override
    public List<Meeting> retrieveAllMeetings() { return meetingDAO.getAllMeetings(); }
}
