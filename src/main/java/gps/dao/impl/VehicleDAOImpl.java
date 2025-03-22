package gps.dao.impl;

import gps.dao.VehicleDAO;
import gps.dto.VehicleDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    private Connection connection;
    
    public VehicleDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public VehicleDTO getVehicleById(int vehicleId) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new VehicleDTO(
                    rs.getInt("vehicle_id"),
                    rs.getInt("vehicle_type_id"),
                    rs.getString("vehicle_number"),
                    rs.getInt("energy_id"),
                    rs.getFloat("max_consumption_rate"),
                    rs.getInt("max_passengers"),
                    rs.getTimestamp("created_at")
                );
            }
        }
        return null;
    }
    
    @Override
    public VehicleDTO getVehicleByNumber(String vehicleNumber) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE vehicle_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vehicleNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new VehicleDTO(
                    rs.getInt("vehicle_id"),
                    rs.getInt("vehicle_type_id"),
                    rs.getString("vehicle_number"),
                    rs.getInt("energy_id"),
                    rs.getFloat("max_consumption_rate"),
                    rs.getInt("max_passengers"),
                    rs.getTimestamp("created_at")
                );
            }
        }
        return null;
    }
    
    @Override
    public List<VehicleDTO> getAllVehicles() throws SQLException {
        List<VehicleDTO> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                vehicles.add(new VehicleDTO(
                    rs.getInt("vehicle_id"),
                    rs.getInt("vehicle_type_id"),
                    rs.getString("vehicle_number"),
                    rs.getInt("energy_id"),
                    rs.getFloat("max_consumption_rate"),
                    rs.getInt("max_passengers"),
                    rs.getTimestamp("created_at")
                ));
            }
        }
        return vehicles;
    }
    
    @Override
    public List<VehicleDTO> getVehiclesByType(int vehicleTypeId) throws SQLException {
        List<VehicleDTO> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE vehicle_type_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleTypeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new VehicleDTO(
                    rs.getInt("vehicle_id"),
                    rs.getInt("vehicle_type_id"),
                    rs.getString("vehicle_number"),
                    rs.getInt("energy_id"),
                    rs.getFloat("max_consumption_rate"),
                    rs.getInt("max_passengers"),
                    rs.getTimestamp("created_at")
                ));
            }
        }
        return vehicles;
    }
    
    @Override
    public List<VehicleDTO> getVehiclesByEnergyType(int energyId) throws SQLException {
        List<VehicleDTO> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE energy_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, energyId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new VehicleDTO(
                    rs.getInt("vehicle_id"),
                    rs.getInt("vehicle_type_id"),
                    rs.getString("vehicle_number"),
                    rs.getInt("energy_id"),
                    rs.getFloat("max_consumption_rate"),
                    rs.getInt("max_passengers"),
                    rs.getTimestamp("created_at")
                ));
            }
        }
        return vehicles;
    }
    
    @Override
    public boolean addVehicle(VehicleDTO vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicles (vehicle_type_id, vehicle_number, energy_id, " +
                    "max_consumption_rate, max_passengers) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicle.getVehicleTypeId());
            stmt.setString(2, vehicle.getVehicleNumber());
            stmt.setInt(3, vehicle.getEnergyId());
            stmt.setFloat(4, vehicle.getMaxConsumptionRate());
            stmt.setInt(5, vehicle.getMaxPassengers());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean updateVehicle(VehicleDTO vehicle) throws SQLException {
        String sql = "UPDATE Vehicles SET vehicle_type_id = ?, vehicle_number = ?, energy_id = ?, " +
                    "max_consumption_rate = ?, max_passengers = ? WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicle.getVehicleTypeId());
            stmt.setString(2, vehicle.getVehicleNumber());
            stmt.setInt(3, vehicle.getEnergyId());
            stmt.setFloat(4, vehicle.getMaxConsumptionRate());
            stmt.setInt(5, vehicle.getMaxPassengers());
            stmt.setInt(6, vehicle.getVehicleId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteVehicle(int vehicleId) throws SQLException {
        String sql = "DELETE FROM Vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            return stmt.executeUpdate() > 0;
        }
    }
} 