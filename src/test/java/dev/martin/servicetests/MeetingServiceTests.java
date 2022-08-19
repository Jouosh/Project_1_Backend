package dev.martin.servicetests;


import dev.martin.data.MeetingDAO;
import dev.martin.entities.Meeting;
import dev.martin.service.MeetingService;
import dev.martin.service.MeetingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class MeetingServiceTests {

    //Create mock DAO and real service
    static MeetingDAO meetingDAO = Mockito.mock(MeetingDAO.class);
    static MeetingService meetingService = new MeetingServiceImpl(meetingDAO);

    @Test
    void registered_meeting_must_have_a_description() {
        Meeting meeting = new Meeting(1, "", "The sewers", 1200);
        Mockito.when(meetingDAO.createMeeting(meeting)).thenReturn(meeting);
        Assertions.assertThrows(RuntimeException.class, () -> meetingService.registerMeeting(meeting));
    }

    @Test
    void registered_meeting_must_have_a_place() {
        Meeting meeting = new Meeting(1, "Sewer talk", "", 1200);
        Mockito.when(meetingDAO.createMeeting(meeting)).thenReturn(meeting);
        Assertions.assertThrows(RuntimeException.class, () -> meetingService.registerMeeting(meeting));
    }

}
