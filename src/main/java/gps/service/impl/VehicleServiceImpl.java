package gps.service.impl;

import gps.dao.VehicleDAO;
import gps.dao.impl.VehicleDAOImpl;
import gps.dto.VehicleDTO;
import gps.service.VehicleService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VehicleServiceImpl implements VehicleService {
    private VehicleDAO vehicleDAO;
    
    public VehicleServiceImpl(Connection connection) {
        this.vehicleDAO = new VehicleDAOImpl(connection);
    }
    
    @Override
    public VehicleDTO getVehicleById(int vehicleId) throws SQLException {
        return vehicleDAO.getVehicleById(vehicleId);
    }
    
    @Override
    public VehicleDTO getVehicleByNumber(String vehicleNumber) throws SQLException {
        return vehicleDAO.getVehicleByNumber(vehicleNumber);
    }
    
    @Override
    public List<VehicleDTO> getAllVehicles() throws SQLException {
        return vehicleDAO.getAllVehicles();
    }
    
    @Override
    public List<VehicleDTO> getVehiclesByType(int vehicleTypeId) throws SQLException {
        return vehicleDAO.getVehiclesByType(vehicleTypeId);
    }
    
    @Override
    public List<VehicleDTO> getVehiclesByEnergyType(int energyId) throws SQLException {
        return vehicleDAO.getVehiclesByEnergyType(energyId);
    }
    
    @Override
    public boolean addVehicle(VehicleDTO vehicle) throws SQLException {
        return vehicleDAO.addVehicle(vehicle);
    }
    
    @Override
    public boolean updateVehicle(VehicleDTO vehicle) throws SQLException {
        return vehicleDAO.updateVehicle(vehicle);
    }
    
    @Override
    public boolean deleteVehicle(int vehicleId) throws SQLException {
        return vehicleDAO.deleteVehicle(vehicleId);
    }
} 