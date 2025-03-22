package gps.service.impl;

import gps.dao.GPSDataDAO;
import gps.dao.impl.GPSDataDAOImpl;
import gps.dto.GPSDataDTO;
import gps.service.GPSDataService;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
/**
 * GPSDataService's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class GPSDataServiceImpl implements GPSDataService {
    private GPSDataDAO gpsDataDAO;
    
    public GPSDataServiceImpl() {
        this.gpsDataDAO = new GPSDataDAOImpl();
    }
    
    @Override
    public GPSDataDTO getGPSDataById(int trackingId) throws SQLException {
        return gpsDataDAO.getGPSDataById(trackingId);
    }
    
    @Override
    public List<GPSDataDTO> getGPSDataByTripId(String tripId)  throws SQLException {
        return gpsDataDAO.getGPSDataByTripId(tripId);
    }
    
    @Override
    public List<GPSDataDTO> getGPSDataByTimeRange(int vehicleId, Timestamp startTime, Timestamp endTime) throws SQLException {
        return gpsDataDAO.getGPSDataByTimeRange(vehicleId, startTime, endTime);
    }
    
    @Override
    public int addGPSData(GPSDataDTO gpsData) throws SQLException {
        return gpsDataDAO.addGPSData(gpsData);
    }
    
    @Override
    public boolean updateGPSData(GPSDataDTO gpsData) throws SQLException {
        return gpsDataDAO.updateGPSData(gpsData);
    }
    
    @Override
    public boolean deleteGPSData(int trackingId) throws SQLException {
        return gpsDataDAO.deleteGPSData(trackingId);
    }
    
    @Override
    public boolean deleteGPSDataByVehicleId(int vehicleId) throws SQLException {
        return gpsDataDAO.deleteGPSDataByVehicleId(vehicleId);
    }
} 