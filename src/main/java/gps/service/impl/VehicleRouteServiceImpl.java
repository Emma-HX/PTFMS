package gps.service.impl;

import gps.dao.VehicleRouteDAO;
import gps.dao.impl.VehicleRouteDAOImpl;
import gps.dto.VehicleRouteDTO;
import gps.service.VehicleRouteService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VehicleRouteServiceImpl implements VehicleRouteService {
    private VehicleRouteDAO vehicleRouteDAO;
    
    public VehicleRouteServiceImpl(Connection connection) {
        this.vehicleRouteDAO = new VehicleRouteDAOImpl(connection);
    }
    
    @Override
    public List<VehicleRouteDTO> getVehicleRoutesByVehicleId(int vehicleId) throws SQLException {
        return vehicleRouteDAO.getVehicleRoutesByVehicleId(vehicleId);
    }
    
    @Override
    public List<VehicleRouteDTO> getVehicleRoutesByRouteId(int routeId) throws SQLException {
        return vehicleRouteDAO.getVehicleRoutesByRouteId(routeId);
    }
    
    @Override
    public boolean addVehicleRoute(VehicleRouteDTO vehicleRoute) throws SQLException {
        return vehicleRouteDAO.addVehicleRoute(vehicleRoute);
    }
    
    @Override
    public boolean deleteVehicleRoute(int vehicleId, int routeId) throws SQLException {
        return vehicleRouteDAO.deleteVehicleRoute(vehicleId, routeId);
    }
    
    @Override
    public boolean deleteAllVehicleRoutesByVehicleId(int vehicleId) throws SQLException {
        return vehicleRouteDAO.deleteAllVehicleRoutesByVehicleId(vehicleId);
    }
    
    @Override
    public boolean deleteAllVehicleRoutesByRouteId(int routeId) throws SQLException {
        return vehicleRouteDAO.deleteAllVehicleRoutesByRouteId(routeId);
    }
} 