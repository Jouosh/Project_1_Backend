package dev.martin.entities;

public class Meeting {

    int meeting_id;
    String description;
    String place;
    int time;

    public Meeting() {
    }

    public Meeting(int meeting_id, String description, String place, int time) {
        this.meeting_id = meeting_id;
        this.description = description;
        this.place = place;
        this.time = time;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meeting_id=" + meeting_id +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                ", time=" + time +
                '}';
    }
}
