package dev.martin.dtos;

//A Data Transfer Object(DTO) is designed just to be a vessel to hold a JSON going from frontend to backend
//minimal logic, not saved to a database
public class LoginCredentials {

    private String username;
    private String password;

    public LoginCredentials() {
    }

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}