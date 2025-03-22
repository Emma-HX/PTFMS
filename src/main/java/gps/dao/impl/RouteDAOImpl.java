package gps.dao.impl;

import gps.dao.RouteDAO;
import gps.dto.RouteDTO;
import gps.dto.StationDTO;
import gps.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RouteDAO's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class RouteDAOImpl implements RouteDAO {
    @Override
    public RouteDTO getRouteById(int routeId) throws SQLException {
        String sql = "SELECT r.*, rs.station_id, s.name, s.location, s.description " +
                    "FROM Routes r " +
                    "JOIN Route_Stations rs ON r.route_id = rs.route_id " +
                    "JOIN Stations s ON rs.station_id = s.station_id " +
                    "WHERE r.route_id = ? " +
                    "ORDER BY rs.sequence";
                    
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {        	
            stmt.setInt(1, routeId);
            try(ResultSet rs = stmt.executeQuery()){
            	List<StationDTO> stations = new ArrayList<>();
	            RouteDTO route = null;
	            while (rs.next()) {
	                if (route == null) {
	                    route = new RouteDTO(
	                        rs.getInt("route_id"),
	                        rs.getString("route_name"),
	                        rs.getInt("start_station_id"),
	                        rs.getInt("end_station_id"),
	                        rs.getFloat("distance"),
	                        rs.getString("description")
	                    );
	                }                
	                StationDTO station = new StationDTO(
	                    rs.getInt("station_id"),
	                    rs.getString("name"),
	                    rs.getString("location"),
	                    rs.getString("description")
	                );
	                stations.add(station);	                
	            }
	            route.setStations(stations);
	            return route;
            }
        }
    }
    
    @Override
    public List<RouteDTO> getAllRoutes() throws SQLException {
        List<RouteDTO> routes = new ArrayList<>();
        String sql = "SELECT * FROM Routes";
        try (Connection conn = DataSource.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql);
        		ResultSet rs = stmt.executeQuery()) {        	
            while (rs.next()) {
            	RouteDTO route = new RouteDTO(
                        rs.getInt("route_id"),
                        rs.getString("route_name"),
                        rs.getInt("start_station_id"),
                        rs.getInt("end_station_id"),
                        rs.getFloat("distance"),
                        rs.getString("description"));            	
                routes.add(route);
            }
        }catch(SQLException e) {
        	System.err.println("Get Routes records error.");
        	e.printStackTrace();
        }
        for (int i = 0; i < routes.size(); i++) {
            routes.set(i, this.getRouteById(routes.get(i).getRouteId()));
        }
        return routes;
    }

} 