package gps.service;

import java.util.List;

import gps.dto.UserVehicleDTO;

/**
 * UserVehicleService defines the business logic for managing user-vehicle relationships.
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface UserVehicleService {

    /**
     * Assigns a vehicle to a user.
     * @param userId The user's ID.
     * @param vehicleId The vehicle's ID.
     */
    void assignVehicleToUser(int userId, int vehicleId);

    /**
     * Removes a vehicle from a user.
     * @param userId The user's ID.
     * @param vehicleId The vehicle's ID.
     */
    void removeVehicleFromUser(int userId, int vehicleId);

    /**
     * Returns a list of vehicles assigned to a specific user.
     * @param userId The user's ID.
     * @return List of UserVehicleDTO.
     */
    List<UserVehicleDTO> getVehiclesByUser(int userId);

    /**
     * Returns a list of users assigned to a specific vehicle.
     * @param vehicleId The vehicle's ID.
     * @return List of UserVehicleDTO.
     */
    List<UserVehicleDTO> getUsersByVehicle(int vehicleId);
}
