package gps.dao.impl;

import gps.dao.RouteStationDAO;
import gps.dto.RouteStationDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RouteStationDAO's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class RouteStationDAOImpl implements RouteStationDAO {
    private Connection connection;
    
    public RouteStationDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public List<RouteStationDTO> getRouteStationsByRouteId(int routeId) throws SQLException {
        List<RouteStationDTO> routeStations = new ArrayList<>();
        String sql = "SELECT * FROM Route_Stations WHERE route_id = ? ORDER BY sequence";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                routeStations.add(new RouteStationDTO(
                    rs.getInt("route_id"),
                    rs.getInt("station_id"),
                    rs.getInt("sequence")
                ));
            }
        }
        return routeStations;
    }
    
    @Override
    public List<RouteStationDTO> getRouteStationsByStationId(int stationId) throws SQLException {
        List<RouteStationDTO> routeStations = new ArrayList<>();
        String sql = "SELECT * FROM Route_Stations WHERE station_id = ? ORDER BY sequence";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stationId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                routeStations.add(new RouteStationDTO(
                    rs.getInt("route_id"),
                    rs.getInt("station_id"),
                    rs.getInt("sequence")
                ));
            }
        }
        return routeStations;
    }
    
    @Override
    public boolean addRouteStation(RouteStationDTO routeStation) throws SQLException {
        String sql = "INSERT INTO Route_Stations (route_id, station_id, sequence) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeStation.getRouteId());
            stmt.setInt(2, routeStation.getStationId());
            stmt.setInt(3, routeStation.getSequence());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean updateRouteStationSequence(int routeId, int stationId, int newSequence) throws SQLException {
        String sql = "UPDATE Route_Stations SET sequence = ? WHERE route_id = ? AND station_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newSequence);
            stmt.setInt(2, routeId);
            stmt.setInt(3, stationId);
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteRouteStation(int routeId, int stationId) throws SQLException {
        String sql = "DELETE FROM Route_Stations WHERE route_id = ? AND station_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeId);
            stmt.setInt(2, stationId);
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteAllRouteStationsByRouteId(int routeId) throws SQLException {
        String sql = "DELETE FROM Route_Stations WHERE route_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeId);
            return stmt.executeUpdate() > 0;
        }
    }
} 