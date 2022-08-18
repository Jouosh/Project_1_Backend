package dev.martin.service;

import dev.martin.data.MeetingDAO;
import dev.martin.entities.Meeting;

import java.util.List;

public class MeetingServiceImpl implements MeetingService{

    //Connection to data layer
    private MeetingDAO meetingDAO;

    //Constructor
    public MeetingServiceImpl(MeetingDAO meetingDAO) { this.meetingDAO = meetingDAO; }

    //Read
    @Override
    public List<Meeting> retrieveAllMeetings() { return meetingDAO.getAllMeetings(); }
}
