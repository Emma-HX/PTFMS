package gps.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import gps.dao.UserVehicleDAO;
import gps.dto.UserVehicleDTO;
import gps.util.DataSource;

/**
 * UserVehicleDAOImpl provides the concrete implementation of the UserVehicleDAO interface.
 * It performs database operations like insert, delete, and query on the user_vehicle table.
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class UserVehicleDAOImpl implements UserVehicleDAO {

    @Override
    public void insertUserVehicle(UserVehicleDTO userVehicle) {
        String sql = "INSERT INTO user_vehicle (user_id, vehicle_id) VALUES (?, ?)";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userVehicle.getUserId());  // Bind userId value
            stmt.setInt(2, userVehicle.getVehicleId()); // Bind vehicleId value
            stmt.executeUpdate();  // Execute the insertion
        } catch (SQLException e) {
            e.printStackTrace();  // Print error if the query fails
        }
    }

    @Override
    public void deleteUserVehicle(int userId, int vehicleId) {
        String sql = "DELETE FROM user_vehicle WHERE user_id = ? AND vehicle_id = ?";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, vehicleId);
            stmt.executeUpdate();  // Execute the deletion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserVehicleDTO> findVehiclesByUserId(int userId) {
        String sql = "SELECT * FROM user_vehicle WHERE user_id = ?";
        List<UserVehicleDTO> userVehicles = new ArrayList<>();
        try (Connection connection = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);  // Set userId parameter
            ResultSet rs = stmt.executeQuery();  // Execute the query
            while (rs.next()) {
                userVehicles.add(new UserVehicleDTO(rs.getInt("user_id"), rs.getInt("vehicle_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userVehicles;
    }

    @Override
    public List<UserVehicleDTO> findUsersByVehicleId(int vehicleId) {
        String sql = "SELECT * FROM user_vehicle WHERE vehicle_id = ?";
        List<UserVehicleDTO> userVehicles = new ArrayList<>();
        try (Connection connection = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userVehicles.add(new UserVehicleDTO(rs.getInt("user_id"), rs.getInt("vehicle_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userVehicles;
    }

    @Override
    public boolean exists(int userId, int vehicleId) {
        String sql = "SELECT 1 FROM user_vehicle WHERE user_id = ? AND vehicle_id = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, vehicleId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Return true if the record exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

