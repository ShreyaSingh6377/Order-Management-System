package entity;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;

      public User(){
      }
      
    // Constructor
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        if (role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("User ")) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Invalid role. Role must be either 'Admin' or 'User '.");
        }
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("User ")) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Invalid role. Role must be either 'Admin' or 'User '.");
        }
    }
}
