package gps.dto;

public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private String passwordHash;
    private int userType;
    
    public UserDTO(int userId, String name, String email, String passwordHash, int userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }
    
    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    
    public int getUserType() { return userType; }
    public void setUserType(int userType) { this.userType = userType; }
} 