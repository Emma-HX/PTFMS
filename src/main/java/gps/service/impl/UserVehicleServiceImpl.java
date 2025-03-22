package gps.service.impl;

import java.util.List;

import gps.dao.UserVehicleDAO;
import gps.dao.impl.UserVehicleDAOImpl;
import gps.dto.UserVehicleDTO;
import gps.service.UserVehicleService;

/**
 * UserVehicleServiceImpl implements the UserVehicleService interface,
 * handling business logic by interacting with the DAO layer.
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class UserVehicleServiceImpl implements UserVehicleService {

    private final UserVehicleDAO userVehicleDAO;  // DAO dependency

    public UserVehicleServiceImpl() {
        this.userVehicleDAO = new UserVehicleDAOImpl();
    }

    @Override
    public void assignVehicleToUser(int userId, int vehicleId) {
        if (!userVehicleDAO.exists(userId, vehicleId)) {
            userVehicleDAO.insertUserVehicle(new UserVehicleDTO(userId, vehicleId));
        } else {
            System.out.println("Vehicle already assigned to this user.");
        }
    }

    @Override
    public void removeVehicleFromUser(int userId, int vehicleId) {
        if (userVehicleDAO.exists(userId, vehicleId)) {
            userVehicleDAO.deleteUserVehicle(userId, vehicleId);
        } else {
            System.out.println("No such assignment exists.");
        }
    }

    @Override
    public List<UserVehicleDTO> getVehiclesByUser(int userId) {
        return userVehicleDAO.findVehiclesByUserId(userId);
    }

    @Override
    public List<UserVehicleDTO> getUsersByVehicle(int vehicleId) {
        return userVehicleDAO.findUsersByVehicleId(vehicleId);
    }
}

