package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.StationDTO;
/**
 * station DAO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface StationDAO {
	/**
	 * get station by id
	 * @param stationId
	 * @return
	 * @throws SQLException
	 */
    StationDTO getStationById(int stationId) throws SQLException;
    /**
     * get all station info
     * @return
     * @throws SQLException
     */
    List<StationDTO> getAllStations() throws SQLException;
    /**
     * get stations by route id
     * @param routeId
     * @return
     * @throws SQLException
     */
    List<StationDTO> getStationsByRouteId(int routeId) throws SQLException;
    /**
     * insert a station
     * @param station
     * @return
     * @throws SQLException
     */
    boolean addStation(StationDTO station) throws SQLException;
    /**
     * update a station info
     * @param station
     * @return
     * @throws SQLException
     */
    boolean updateStation(StationDTO station) throws SQLException;
    /**
     * delete a station
     * @param stationId
     * @return
     * @throws SQLException
     */
    boolean deleteStation(int stationId) throws SQLException;
    /**
     * get station by name
     * @param stationName
     * @return
     * @throws SQLException
     */
    public StationDTO getStationByName(String stationName) throws SQLException;
} 