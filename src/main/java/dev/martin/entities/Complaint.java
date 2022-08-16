package dev.martin.entities;

public class Complaint {

    private int id;
    private int priority;
    private int complainer;
    private Status status;
    private String type;
    private String description;

    public Complaint() {

    }

    public Complaint(int id, int priority, int complainer, Status status, String type, String description) {
        this.id = id;
        this.priority = priority;
        this.complainer = complainer;
        this.status = status;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getComplainer() {
        return complainer;
    }

    public void setComplainer(int complainer) {
        this.complainer = complainer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", priority=" + priority +
                ", complainer=" + complainer +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
