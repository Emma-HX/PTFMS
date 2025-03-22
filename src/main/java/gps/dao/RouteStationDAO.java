package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.RouteStationDTO;

/**
 * routeStation DAO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface RouteStationDAO {
	/**
	 * get route_station records by route id
	 * @param routeId
	 * @return
	 * @throws SQLException
	 */
    List<RouteStationDTO> getRouteStationsByRouteId(int routeId) throws SQLException;
    /**
     * get route_station records by station id 
     * @param stationId
     * @return
     * @throws SQLException
     */
    List<RouteStationDTO> getRouteStationsByStationId(int stationId) throws SQLException;
    /**
     * insert a new record
     * @param routeStation
     * @return
     * @throws SQLException
     */
    boolean addRouteStation(RouteStationDTO routeStation) throws SQLException;
    /**
     * update a record
     * @param routeId
     * @param stationId
     * @param newSequence
     * @return
     * @throws SQLException
     */
    boolean updateRouteStationSequence(int routeId, int stationId, int newSequence) throws SQLException;
    /**
     * delete a record
     * @param routeId
     * @param stationId
     * @return
     * @throws SQLException
     */
    boolean deleteRouteStation(int routeId, int stationId) throws SQLException;
    /**
     * delete all records
     * @param routeId
     * @return
     * @throws SQLException
     */
    boolean deleteAllRouteStationsByRouteId(int routeId) throws SQLException;
} 