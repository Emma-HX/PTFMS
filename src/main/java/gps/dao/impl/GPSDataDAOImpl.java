package gps.dao.impl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import gps.dao.GPSDataDAO;
import gps.dto.GPSDataDTO;
import gps.util.DataSource;

/**
 * GPSDataDAO's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class GPSDataDAOImpl implements GPSDataDAO {
       
    @Override
    public GPSDataDTO getGPSDataById(int trackingId) throws SQLException {
        String sql = "SELECT * FROM GPS_data WHERE tracking_id = ?";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, trackingId);
            try(ResultSet rs = stmt.executeQuery();){
                if (rs.next()) {
                    return new GPSDataDTO(
                        rs.getInt("tracking_id"),
                        rs.getString("trip_id"),
                        rs.getInt("route_id"),
                        rs.getInt("vehicle_id"),
                        rs.getObject("station_id", Integer.class),
                        rs.getString("location"),
                        rs.getTimestamp("arrival_time"),
                        rs.getTimestamp("departure_time"),
                        rs.getString("status")
                    );
                }
            }

        }
        return null;
    }
    
    @Override
    public List<GPSDataDTO> getGPSDataByTripId(String tripId) throws SQLException {
        List<GPSDataDTO> gpsDataList = new ArrayList<>();
        String sql = "SELECT * FROM GPS_data WHERE trip_id = ? ORDER BY arrival_time";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tripId);
            try(ResultSet rs = stmt.executeQuery();){
                while (rs.next()) {
                    gpsDataList.add(new GPSDataDTO(
                        rs.getInt("tracking_id"),
                        rs.getString("trip_id"),
                        rs.getInt("route_id"),
                        rs.getInt("vehicle_id"),
                        rs.getObject("station_id", Integer.class),
                        rs.getString("location"),
                        rs.getTimestamp("arrival_time"),
                        rs.getTimestamp("departure_time"),
                        rs.getString("status")
                    ));
                }
            }

        }
        return gpsDataList;
    }
        
    @Override
    public List<GPSDataDTO> getGPSDataByTimeRange(int vehicleId, java.sql.Timestamp startTime, java.sql.Timestamp endTime) throws SQLException {
        List<GPSDataDTO> gpsDataList = new ArrayList<>();
        String sql = "SELECT * FROM GPS_data WHERE vehicle_id = ? AND arrival_time BETWEEN ? AND ? ORDER BY arrival_time";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.setTimestamp(2, startTime);
            stmt.setTimestamp(3, endTime);
            try(ResultSet rs = stmt.executeQuery();){
	            while (rs.next()) {
	                gpsDataList.add(new GPSDataDTO(
	                    rs.getInt("tracking_id"),
	                    rs.getString("trip_id"),
                        rs.getInt("route_id"),
	                    rs.getInt("vehicle_id"),
	                    rs.getObject("station_id", Integer.class),
	                    rs.getString("location"),
	                    rs.getTimestamp("arrival_time"),
	                    rs.getTimestamp("departure_time"),
	                    rs.getString("status")
	                ));
	            }
            }
        }
        return gpsDataList;
    }
    
    @Override
    public int addGPSData(GPSDataDTO gpsData) throws SQLException {
        String sql = "INSERT INTO GPS_data (trip_id, route_id, vehicle_id, station_id, location, arrival_time, " +
                    "departure_time, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
        	stmt.setString(1, gpsData.getTripId());
        	stmt.setInt(2,gpsData.getRouteId());
            stmt.setInt(3, gpsData.getVehicleId());
            stmt.setObject(4, gpsData.getStationId());
            stmt.setString(5, gpsData.getLocation());
            stmt.setTimestamp(6, gpsData.getArrivalTime());
            stmt.setTimestamp(7, gpsData.getDepartureTime());
            stmt.setString(8, gpsData.getStatus());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {  
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {  
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); 
                    }
                }
            }
            return -1;
        }
    }
    
    @Override
    public boolean updateGPSData(GPSDataDTO gpsData) throws SQLException {
        String sql = "UPDATE GPS_data SET vehicle_id = ?, station_id = ?, location = ?, " +
                    "arrival_time = ?, departure_time = ?, status = ? WHERE tracking_id = ?";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gpsData.getVehicleId());
            stmt.setObject(2, gpsData.getStationId());
            stmt.setString(3, gpsData.getLocation());
            stmt.setTimestamp(4, gpsData.getArrivalTime());
            stmt.setTimestamp(5, gpsData.getDepartureTime());
            stmt.setString(6, gpsData.getStatus());
            stmt.setInt(7, gpsData.getTrackingId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteGPSData(int trackingId) throws SQLException {
        String sql = "DELETE FROM GPS_data WHERE tracking_id = ?";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, trackingId);
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteGPSDataByVehicleId(int vehicleId) throws SQLException {
        String sql = "DELETE FROM GPS_data WHERE vehicle_id = ?";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            return stmt.executeUpdate() > 0;
        }
    }
} 