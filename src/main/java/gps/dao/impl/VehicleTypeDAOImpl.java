package gps.dao.impl;

import gps.dao.VehicleTypeDAO;
import gps.dto.VehicleTypeDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleTypeDAOImpl implements VehicleTypeDAO {
    private Connection connection;
    
    public VehicleTypeDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public VehicleTypeDTO getVehicleTypeById(int typeId) throws SQLException {
        String sql = "SELECT * FROM Vehicle_Type WHERE type_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, typeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new VehicleTypeDTO(
                    rs.getInt("type_id"),
                    rs.getString("type_name"),
                    rs.getString("description")
                );
            }
        }
        return null;
    }
    
    @Override
    public List<VehicleTypeDTO> getAllVehicleTypes() throws SQLException {
        List<VehicleTypeDTO> vehicleTypes = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle_Type";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                vehicleTypes.add(new VehicleTypeDTO(
                    rs.getInt("type_id"),
                    rs.getString("type_name"),
                    rs.getString("description")
                ));
            }
        }
        return vehicleTypes;
    }
    
    @Override
    public boolean addVehicleType(VehicleTypeDTO vehicleType) throws SQLException {
        String sql = "INSERT INTO Vehicle_Type (type_name, description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vehicleType.getTypeName());
            stmt.setString(2, vehicleType.getDescription());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean updateVehicleType(VehicleTypeDTO vehicleType) throws SQLException {
        String sql = "UPDATE Vehicle_Type SET type_name = ?, description = ? WHERE type_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vehicleType.getTypeName());
            stmt.setString(2, vehicleType.getDescription());
            stmt.setInt(3, vehicleType.getTypeId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteVehicleType(int typeId) throws SQLException {
        String sql = "DELETE FROM Vehicle_Type WHERE type_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, typeId);
            return stmt.executeUpdate() > 0;
        }
    }
} 