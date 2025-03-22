package gps.dao.impl;

import gps.dao.VehicleRouteDAO;
import gps.dto.VehicleRouteDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRouteDAOImpl implements VehicleRouteDAO {
    private Connection connection;
    
    public VehicleRouteDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public List<VehicleRouteDTO> getVehicleRoutesByVehicleId(int vehicleId) throws SQLException {
        List<VehicleRouteDTO> vehicleRoutes = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle_Route WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicleRoutes.add(new VehicleRouteDTO(
                    rs.getInt("vehicle_id"),
                    rs.getInt("route_id")
                ));
            }
        }
        return vehicleRoutes;
    }
    
    @Override
    public List<VehicleRouteDTO> getVehicleRoutesByRouteId(int routeId) throws SQLException {
        List<VehicleRouteDTO> vehicleRoutes = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle_Route WHERE route_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicleRoutes.add(new VehicleRouteDTO(
                    rs.getInt("vehicle_id"),
                    rs.getInt("route_id")
                ));
            }
        }
        return vehicleRoutes;
    }
    
    @Override
    public boolean addVehicleRoute(VehicleRouteDTO vehicleRoute) throws SQLException {
        String sql = "INSERT INTO Vehicle_Route (vehicle_id, route_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleRoute.getVehicleId());
            stmt.setInt(2, vehicleRoute.getRouteId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteVehicleRoute(int vehicleId, int routeId) throws SQLException {
        String sql = "DELETE FROM Vehicle_Route WHERE vehicle_id = ? AND route_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.setInt(2, routeId);
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteAllVehicleRoutesByVehicleId(int vehicleId) throws SQLException {
        String sql = "DELETE FROM Vehicle_Route WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteAllVehicleRoutesByRouteId(int routeId) throws SQLException {
        String sql = "DELETE FROM Vehicle_Route WHERE route_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeId);
            return stmt.executeUpdate() > 0;
        }
    }
} 