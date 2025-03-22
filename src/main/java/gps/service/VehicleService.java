package gps.service;

import gps.dto.VehicleDTO;
import java.sql.SQLException;
import java.util.List;

public interface VehicleService {
    VehicleDTO getVehicleById(int vehicleId) throws SQLException;
    VehicleDTO getVehicleByNumber(String vehicleNumber) throws SQLException;
    List<VehicleDTO> getAllVehicles() throws SQLException;
    List<VehicleDTO> getVehiclesByType(int vehicleTypeId) throws SQLException;
    List<VehicleDTO> getVehiclesByEnergyType(int energyId) throws SQLException;
    boolean addVehicle(VehicleDTO vehicle) throws SQLException;
    boolean updateVehicle(VehicleDTO vehicle) throws SQLException;
    boolean deleteVehicle(int vehicleId) throws SQLException;
} 