package gps.dto;
/**
 * user vehicle DTO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class UserVehicleDTO {
    private int userId;
    private int vehicleId;

    // constructor
    public UserVehicleDTO() {}

    // constructor with parameters
    public UserVehicleDTO(int userId, int vehicleId) {
        this.userId = userId;
        this.vehicleId = vehicleId;
    }

    // Getters 和 Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "UserVehicleDTO{" +
                "userId=" + userId +
                ", vehicleId=" + vehicleId +
                '}';
    }
}

