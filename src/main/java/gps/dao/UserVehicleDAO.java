package gps.dao;

import java.util.List;

import gps.dto.UserVehicleDTO;

/**
 * user vehicle DAO interface
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/


public interface UserVehicleDAO {

    /**
     * Inserts a new user-vehicle mapping into the database.
     * @param userVehicle The UserVehicleDTO object representing the new record.
     */
    void insertUserVehicle(UserVehicleDTO userVehicle);

    /**
     * Deletes a user-vehicle mapping from the database.
     * @param userId The ID of the user.
     * @param vehicleId The ID of the vehicle.
     */
    void deleteUserVehicle(int userId, int vehicleId);

    /**
     * Finds all vehicle records assigned to a given user.
     * @param userId The ID of the user.
     * @return List of UserVehicleDTO objects.
     */
    List<UserVehicleDTO> findVehiclesByUserId(int userId);

    /**
     * Finds all user records assigned to a given vehicle.
     * @param vehicleId The ID of the vehicle.
     * @return List of UserVehicleDTO objects.
     */
    List<UserVehicleDTO> findUsersByVehicleId(int vehicleId);

    /**
     * Checks if a user-vehicle mapping exists in the database.
     * @param userId The ID of the user.
     * @param vehicleId The ID of the vehicle.
     * @return true if the mapping exists, false otherwise.
     */
    boolean exists(int userId, int vehicleId);
}

