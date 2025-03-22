package gps.service.impl;

import gps.dao.StationDAO;
import gps.dao.impl.StationDAOImpl;
import gps.dto.StationDTO;
import gps.service.StationService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * StationService's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class StationServiceImpl implements StationService {
    private StationDAO stationDAO;
    
    public StationServiceImpl(Connection connection) {
        this.stationDAO = new StationDAOImpl(connection);
    }
    
    @Override
    public StationDTO getStationById(int stationId) throws SQLException {
        return stationDAO.getStationById(stationId);
    }
    
    @Override
    public StationDTO getStationByName(String stationName) throws SQLException {
        return stationDAO.getStationByName(stationName);
    }
    
    @Override
    public List<StationDTO> getAllStations() throws SQLException {
        return stationDAO.getAllStations();
    }
    
    @Override
    public boolean addStation(StationDTO station) throws SQLException {
        return stationDAO.addStation(station);
    }
    
    @Override
    public boolean updateStation(StationDTO station) throws SQLException {
        return stationDAO.updateStation(station);
    }
    
    @Override
    public boolean deleteStation(int stationId) throws SQLException {
        return stationDAO.deleteStation(stationId);
    }
} 