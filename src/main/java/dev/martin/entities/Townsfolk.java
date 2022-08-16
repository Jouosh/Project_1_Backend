package dev.martin.entities;

public class Townsfolk {

    private int id;
    private String fname;
    private String lname;
    private String username;
    private String pass;
    private String email;
    private Position position;

    public Townsfolk() {

    }

    public Townsfolk(int id, String fname, String lname, String username, String pass, String email, Position position) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Townsfolk{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", job=" + position +
                '}';
    }

}
