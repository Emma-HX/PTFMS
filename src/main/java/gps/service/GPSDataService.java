package gps.service;

import gps.dto.GPSDataDTO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
/**
 * GPSData business layer
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface GPSDataService {
    GPSDataDTO getGPSDataById(int trackingId) throws SQLException;
    List<GPSDataDTO> getGPSDataByTripId(String tripId) throws SQLException;
    List<GPSDataDTO> getGPSDataByTimeRange(int vehicleId, Timestamp startTime, Timestamp endTime) throws SQLException;
    int addGPSData(GPSDataDTO gpsData) throws SQLException;
    boolean updateGPSData(GPSDataDTO gpsData) throws SQLException;
    boolean deleteGPSData(int trackingId) throws SQLException;
    boolean deleteGPSDataByVehicleId(int vehicleId) throws SQLException;
} 