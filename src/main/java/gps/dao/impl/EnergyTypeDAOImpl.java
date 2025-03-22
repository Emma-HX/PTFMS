package gps.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import gps.dao.EnergyTypeDAO;
import gps.dto.EnergyTypeDTO;

/**
 * EnergyTypeDAO's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class EnergyTypeDAOImpl implements EnergyTypeDAO {
    private Connection connection;
    
    public EnergyTypeDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public EnergyTypeDTO getEnergyTypeById(int energyId) throws SQLException {
        String sql = "SELECT * FROM energy_Type WHERE energy_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, energyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EnergyTypeDTO(
                    rs.getInt("energy_id"),
                    rs.getString("energy_name"),
                    rs.getString("description")
                );
            }
        }
        return null;
    }
    
    @Override
    public List<EnergyTypeDTO> getAllEnergyTypes() throws SQLException {
        List<EnergyTypeDTO> energyTypes = new ArrayList<>();
        String sql = "SELECT * FROM energy_Type";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                energyTypes.add(new EnergyTypeDTO(
                    rs.getInt("energy_id"),
                    rs.getString("energy_name"),
                    rs.getString("description")
                ));
            }
        }
        return energyTypes;
    }
    
    @Override
    public boolean addEnergyType(EnergyTypeDTO energyType) throws SQLException {
        String sql = "INSERT INTO energy_Type (energy_name, description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, energyType.getEnergyName());
            stmt.setString(2, energyType.getDescription());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean updateEnergyType(EnergyTypeDTO energyType) throws SQLException {
        String sql = "UPDATE energy_Type SET energy_name = ?, description = ? WHERE energy_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, energyType.getEnergyName());
            stmt.setString(2, energyType.getDescription());
            stmt.setInt(3, energyType.getEnergyId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteEnergyType(int energyId) throws SQLException {
        String sql = "DELETE FROM energy_Type WHERE energy_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, energyId);
            return stmt.executeUpdate() > 0;
        }
    }
} 