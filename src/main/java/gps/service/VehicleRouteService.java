package gps.service;

import gps.dto.VehicleRouteDTO;
import java.sql.SQLException;
import java.util.List;

public interface VehicleRouteService {
    List<VehicleRouteDTO> getVehicleRoutesByVehicleId(int vehicleId) throws SQLException;
    List<VehicleRouteDTO> getVehicleRoutesByRouteId(int routeId) throws SQLException;
    boolean addVehicleRoute(VehicleRouteDTO vehicleRoute) throws SQLException;
    boolean deleteVehicleRoute(int vehicleId, int routeId) throws SQLException;
    boolean deleteAllVehicleRoutesByVehicleId(int vehicleId) throws SQLException;
    boolean deleteAllVehicleRoutesByRouteId(int routeId) throws SQLException;
} 