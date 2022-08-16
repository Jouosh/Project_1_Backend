package dev.martin.entities;

public class Meeting {

    private int id;
    private int organizer;
    private int complaint;
    private String meetingPlace;
    private String meetingTime;

    public Meeting() {
    }

    public Meeting(int id, int organizer, int complaint, String meetingPlace, String meetingTime) {
        this.id = id;
        this.organizer = organizer;
        this.complaint = complaint;
        this.meetingPlace = meetingPlace;
        this.meetingTime = meetingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganizer() {
        return organizer;
    }

    public void setOrganizer(int organizer) {
        this.organizer = organizer;
    }

    public int getComplaint() {
        return complaint;
    }

    public void setComplaint(int complaint) {
        this.complaint = complaint;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", organizer=" + organizer +
                ", complaint=" + complaint +
                ", meetingPlace='" + meetingPlace + '\'' +
                ", meetingTime='" + meetingTime + '\'' +
                '}';
    }
}
