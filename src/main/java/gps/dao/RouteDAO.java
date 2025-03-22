package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.RouteDTO;
/**
 * Route entity DAO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface RouteDAO {
	/**
	 * get route record by id
	 * @param routeId
	 * @return
	 * @throws SQLException
	 */
    RouteDTO getRouteById(int routeId) throws SQLException;
    /**
     * get all routes' info
     * @return
     * @throws SQLException
     */
    List<RouteDTO> getAllRoutes() throws SQLException;
} 