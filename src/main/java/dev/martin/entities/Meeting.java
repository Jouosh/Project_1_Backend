package dev.martin.entities;

public class Meeting {

    int meetingId;
    String description;
    String place;
    long time;

    public Meeting() {
    }

    public Meeting(int meetingId, String description, String place, long time) {
        this.meetingId = meetingId;
        this.description = description;
        this.place = place;
        this.time = time;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meeting_id=" + meetingId +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                ", time=" + time +
                '}';
    }
}
