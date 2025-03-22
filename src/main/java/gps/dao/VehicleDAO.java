package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.VehicleDTO;

public interface VehicleDAO {
    VehicleDTO getVehicleById(int vehicleId) throws SQLException;
    VehicleDTO getVehicleByNumber(String vehicleNumber) throws SQLException;
    List<VehicleDTO> getAllVehicles() throws SQLException;
    List<VehicleDTO> getVehiclesByType(int vehicleTypeId) throws SQLException;
    List<VehicleDTO> getVehiclesByEnergyType(int energyId) throws SQLException;
    boolean addVehicle(VehicleDTO vehicle) throws SQLException;
    boolean updateVehicle(VehicleDTO vehicle) throws SQLException;
    boolean deleteVehicle(int vehicleId) throws SQLException;
} 