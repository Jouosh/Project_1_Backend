package dev.martin.entities;

public class Complaint {

    int complaintId;
    String description;
    int meeting;
    Status status;

    public Complaint() {

    }

    public Complaint(int complaintId, String description, int meeting, Status status) {
        this.complaintId = complaintId;
        this.description = description;
        this.meeting = meeting;
        this.status = status;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMeeting() {
        return meeting;
    }

    public void setMeeting(int meeting) {
        this.meeting = meeting;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", description='" + description + '\'' +
                ", meeting=" + meeting +
                ", status=" + status +
                '}';
    }

}
