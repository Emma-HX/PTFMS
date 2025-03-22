package gps.dao.impl;

import gps.dao.StationDAO;
import gps.dto.StationDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StationDAO's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class StationDAOImpl implements StationDAO {
    private Connection connection;
    
    public StationDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public StationDTO getStationById(int stationId) throws SQLException {
        String sql = "SELECT * FROM Stations WHERE station_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new StationDTO(
                    rs.getInt("station_id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("description")
                );
            }
        }
        return null;
    }
    
    @Override
    public List<StationDTO> getAllStations() throws SQLException {
        List<StationDTO> stations = new ArrayList<>();
        String sql = "SELECT * FROM Stations";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                stations.add(new StationDTO(
                    rs.getInt("station_id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("description")
                ));
            }
        }
        return stations;
    }
    
    @Override
    public List<StationDTO> getStationsByRouteId(int routeId) throws SQLException {
        List<StationDTO> stations = new ArrayList<>();
        String sql = "SELECT s.* FROM Stations s " +
                    "JOIN Route_Stations rs ON s.station_id = rs.station_id " +
                    "WHERE rs.route_id = ? " +
                    "ORDER BY rs.sequence";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stations.add(new StationDTO(
                    rs.getInt("station_id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("description")
                ));
            }
        }
        return stations;
    }
    
    @Override
    public boolean addStation(StationDTO station) throws SQLException {
        String sql = "INSERT INTO Stations (name, location, description) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, station.getName());
            stmt.setString(2, station.getLocation());
            stmt.setString(3, station.getDescription());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean updateStation(StationDTO station) throws SQLException {
        String sql = "UPDATE Stations SET name = ?, location = ?, description = ? WHERE station_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, station.getName());
            stmt.setString(2, station.getLocation());
            stmt.setString(3, station.getDescription());
            stmt.setInt(4, station.getStationId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteStation(int stationId) throws SQLException {
        String sql = "DELETE FROM Stations WHERE station_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stationId);
            return stmt.executeUpdate() > 0;
        }
    }

	@Override
	public StationDTO getStationByName(String stationName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
} 