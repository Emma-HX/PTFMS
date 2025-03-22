package gps.service;

import gps.dto.StationDTO;
import java.sql.SQLException;
import java.util.List;
/**
 * station business layer
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface StationService {
    StationDTO getStationById(int stationId) throws SQLException;
    StationDTO getStationByName(String stationName) throws SQLException;
    List<StationDTO> getAllStations() throws SQLException;
    boolean addStation(StationDTO station) throws SQLException;
    boolean updateStation(StationDTO station) throws SQLException;
    boolean deleteStation(int stationId) throws SQLException;
} 