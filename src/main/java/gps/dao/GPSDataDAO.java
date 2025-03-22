package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.GPSDataDTO;

/**
 * GPSData DAO interface
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface GPSDataDAO {
	/**
	 * get GPSData by id
	 * @param trackingId
	 * @return
	 * @throws SQLException
	 */
    GPSDataDTO getGPSDataById(int trackingId) throws SQLException;
    /**
     * get GPS DATA by trip id
     * @param tripId
     * @return
     * @throws SQLException
     */
    List<GPSDataDTO> getGPSDataByTripId(String tripId) throws SQLException;
    /**
     * get GPA DATA by time range
     * @param vehicleId
     * @param startTime
     * @param endTime
     * @return
     * @throws SQLException
     */
    List<GPSDataDTO> getGPSDataByTimeRange(int vehicleId, java.sql.Timestamp startTime, java.sql.Timestamp endTime) throws SQLException;
    /**
     * insert a new GPSDATA
     * @param gpsData
     * @return
     * @throws SQLException
     */
    int addGPSData(GPSDataDTO gpsData) throws SQLException;
    /**
     * Uupdate a GPSData
     * @param gpsData
     * @return
     * @throws SQLException
     */
    boolean updateGPSData(GPSDataDTO gpsData) throws SQLException;
    /**
     * delete a GPSData record
     * @param trackingId
     * @return
     * @throws SQLException
     */
    boolean deleteGPSData(int trackingId) throws SQLException;
    /**
     * delete GPSData records by vehicle id
     * @param vehicleId
     * @return
     * @throws SQLException
     */
    boolean deleteGPSDataByVehicleId(int vehicleId) throws SQLException;
} 