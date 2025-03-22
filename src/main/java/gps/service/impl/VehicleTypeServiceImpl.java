package gps.service.impl;

import gps.dao.VehicleTypeDAO;
import gps.dao.impl.VehicleTypeDAOImpl;
import gps.dto.VehicleTypeDTO;
import gps.service.VehicleTypeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VehicleTypeServiceImpl implements VehicleTypeService {
    private VehicleTypeDAO vehicleTypeDAO;
    
    public VehicleTypeServiceImpl(Connection connection) {
        this.vehicleTypeDAO = new VehicleTypeDAOImpl(connection);
    }
    
    @Override
    public VehicleTypeDTO getVehicleTypeById(int typeId) throws SQLException {
        return vehicleTypeDAO.getVehicleTypeById(typeId);
    }
    
    @Override
    public List<VehicleTypeDTO> getAllVehicleTypes() throws SQLException {
        return vehicleTypeDAO.getAllVehicleTypes();
    }
    
    @Override
    public boolean addVehicleType(VehicleTypeDTO vehicleType) throws SQLException {
        return vehicleTypeDAO.addVehicleType(vehicleType);
    }
    
    @Override
    public boolean updateVehicleType(VehicleTypeDTO vehicleType) throws SQLException {
        return vehicleTypeDAO.updateVehicleType(vehicleType);
    }
    
    @Override
    public boolean deleteVehicleType(int typeId) throws SQLException {
        return vehicleTypeDAO.deleteVehicleType(typeId);
    }
} 