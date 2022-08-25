package dev.martin.entities;

public class Townsperson {

    private int townId;
    private String username;
    private String password;
    private Role role;
    private boolean approved;

    public Townsperson() {

    }

    public Townsperson(int townId, String username, String password, Role role, boolean approved) {
        this.townId = townId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.approved = approved;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Townsperson{" +
                "townId=" + townId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", approved=" + approved +
                '}';
    }
}
