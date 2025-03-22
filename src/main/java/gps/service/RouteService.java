package gps.service;

import gps.dto.RouteDTO;
import java.sql.SQLException;
import java.util.List;
/**
 * Route business layer
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface RouteService {
    RouteDTO getRouteById(int routeId) throws SQLException;
    List<RouteDTO> getAllRoutes() throws SQLException;
//    List<RouteDTO> getRoutesByStartStation(int startStationId) throws SQLException;
//    List<RouteDTO> getRoutesByEndStation(int endStationId) throws SQLException;
//    boolean addRoute(RouteDTO route) throws SQLException;
//    boolean updateRoute(RouteDTO route) throws SQLException;
//    boolean deleteRoute(int routeId) throws SQLException;
} 