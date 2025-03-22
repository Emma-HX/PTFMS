package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.VehicleRouteDTO;

public interface VehicleRouteDAO {
    List<VehicleRouteDTO> getVehicleRoutesByVehicleId(int vehicleId) throws SQLException;
    List<VehicleRouteDTO> getVehicleRoutesByRouteId(int routeId) throws SQLException;
    boolean addVehicleRoute(VehicleRouteDTO vehicleRoute) throws SQLException;
    boolean deleteVehicleRoute(int vehicleId, int routeId) throws SQLException;
    boolean deleteAllVehicleRoutesByVehicleId(int vehicleId) throws SQLException;
    boolean deleteAllVehicleRoutesByRouteId(int routeId) throws SQLException;
} 