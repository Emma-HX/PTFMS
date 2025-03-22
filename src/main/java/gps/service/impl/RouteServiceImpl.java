package gps.service.impl;

import gps.dao.RouteDAO;
import gps.dao.impl.RouteDAOImpl;
import gps.dto.RouteDTO;
import gps.service.RouteService;
import java.sql.SQLException;
import java.util.List;
/**
 * RouteService's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class RouteServiceImpl implements RouteService {
    private RouteDAO routeDAO;
    
    public RouteServiceImpl() {
        this.routeDAO = new RouteDAOImpl();
    }
    
    @Override
    public RouteDTO getRouteById(int routeId) throws SQLException {
        return routeDAO.getRouteById(routeId);
    }
    
    @Override
    public List<RouteDTO> getAllRoutes() throws SQLException {
        return routeDAO.getAllRoutes();
    }
} 