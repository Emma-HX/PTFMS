package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.VehicleTypeDTO;

public interface VehicleTypeDAO {
    VehicleTypeDTO getVehicleTypeById(int typeId) throws SQLException;
    List<VehicleTypeDTO> getAllVehicleTypes() throws SQLException;
    boolean addVehicleType(VehicleTypeDTO vehicleType) throws SQLException;
    boolean updateVehicleType(VehicleTypeDTO vehicleType) throws SQLException;
    boolean deleteVehicleType(int typeId) throws SQLException;
} 