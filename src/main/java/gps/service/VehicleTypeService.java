package gps.service;

import gps.dto.VehicleTypeDTO;
import java.sql.SQLException;
import java.util.List;

public interface VehicleTypeService {
    VehicleTypeDTO getVehicleTypeById(int typeId) throws SQLException;
    List<VehicleTypeDTO> getAllVehicleTypes() throws SQLException;
    boolean addVehicleType(VehicleTypeDTO vehicleType) throws SQLException;
    boolean updateVehicleType(VehicleTypeDTO vehicleType) throws SQLException;
    boolean deleteVehicleType(int typeId) throws SQLException;
} 