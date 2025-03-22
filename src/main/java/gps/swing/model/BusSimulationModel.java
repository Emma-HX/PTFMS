package gps.swing.model;

import gps.dto.RouteDTO;
import gps.dto.StationDTO;
import gps.dto.GPSDataDTO;
import gps.service.GPSDataService;
import gps.service.RouteService;
import gps.util.TripIdGenerator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
/**
 * Model layer of the GPS simulator swing
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class BusSimulationModel {
    private RouteDTO currentRoute;
    private List<StationDTO> currentPath;
    private int currentStationIndex = 0;
    private boolean isReturnTrip = false;
    private boolean isMoving = false;
    private boolean isAtStation = false;
    private boolean isMovingBetweenStations = false;
    private LocalTime startTime;
    private LocalTime simulationTime;
    
    // Constants
    private static final int STATION_STOP_TIME = 2; // 2 seconds at each station
    private static final int TIME_SCALE = 200; // 1 real second = 200 simulation seconds
    public static final int TOTAL_STEPS = 50; 
    private int remainingSteps = TOTAL_STEPS;
    private int stationWaitTime = 0;
    
    // Services
    private final GPSDataService gpsDataService;
    private final RouteService routeService;
    
    // GPS tracking
    private int vehicleId;
    private GPSDataDTO currentGpsData;
    private String tripId;

    public BusSimulationModel(GPSDataService gpsDataService, RouteService routeService) {
        this.gpsDataService = gpsDataService;
        this.routeService = routeService;
        this.currentPath = new ArrayList<>();
        this.tripId = TripIdGenerator.getNextTripId();
    }

    public void setRoute(RouteDTO route) {
        this.currentRoute = route;
        resetState();
    }

    public void resetState() {
        currentStationIndex = 0;
        isReturnTrip = false;
        isMoving = false;
        isAtStation = false;
        isMovingBetweenStations = false;
        simulationTime = LocalTime.now();
        startTime = null;
        currentGpsData = null;
        remainingSteps = TOTAL_STEPS;
        stationWaitTime = 0;
        tripId = TripIdGenerator.getNextTripId();
        updateCurrentPath();
    }

    private void updateCurrentPath() {
        if (currentRoute == null) return;
        currentPath = new ArrayList<>(currentRoute.getStations());
    }

    public void updateSimulationTime() {
        if (startTime == null) {
            startTime = LocalTime.now();
            simulationTime = LocalTime.now();
            return;
        }
        
        long elapsedSeconds = (LocalTime.now().toSecondOfDay() - startTime.toSecondOfDay());
        if (elapsedSeconds < 0) {
            elapsedSeconds += 24 * 60 * 60;
        }
        simulationTime = startTime.plusSeconds(elapsedSeconds * TIME_SCALE);
    }

    public void recordGPSData(boolean isArrival) throws SQLException {
        if (currentRoute == null || currentPath.isEmpty()) return;

        StationDTO currentStation = currentPath.get(currentStationIndex);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), simulationTime));
        if (isArrival) {
            currentGpsData = new GPSDataDTO(
                tripId,
                currentRoute.getRouteId(),
                vehicleId,
                currentStation.getStationId(),
                currentStation.getName(),
                timestamp,
                null,
                "In Service"
            );
            int id = gpsDataService.addGPSData(currentGpsData);
            currentGpsData.setTrackingId(id);
        } else if (currentGpsData != null) {
            currentGpsData.setDepartureTime(timestamp);
            gpsDataService.updateGPSData(currentGpsData);
        } else if (currentGpsData == null) {
            currentGpsData = new GPSDataDTO(
                    tripId,
                    currentRoute.getRouteId(),
                    vehicleId,
                    currentStation.getStationId(),
                    currentStation.getName(),
                    null,
                    timestamp,                    
                    "In Service");
            int id = gpsDataService.addGPSData(currentGpsData);
            currentGpsData.setTrackingId(id);
        }
    }

    public List<RouteDTO> getAllRoutes() throws SQLException {
        return routeService.getAllRoutes();
    }

    // Getters
    public RouteDTO getCurrentRoute() { return currentRoute; }
    public List<StationDTO> getCurrentPath() { return currentPath; }
    public int getCurrentStationIndex() { return currentStationIndex; }
    public boolean isReturnTrip() { return isReturnTrip; }
    public boolean isMoving() { return isMoving; }
    public boolean isAtStation() { return isAtStation; }
    public boolean isMovingBetweenStations() { return isMovingBetweenStations; }
    public LocalTime getSimulationTime() { return simulationTime; }
    public int getRemainingSteps() { return remainingSteps; }
    public int getStationWaitTime() { return stationWaitTime; }
    public static int getStationStopTime() { return STATION_STOP_TIME; }
    public static int getTimeScale() { return TIME_SCALE; }
    public static int getTotalSteps() { return TOTAL_STEPS; }
    public String getTripId() { return tripId; }
    public int getVehicleId() { return vehicleId; }
    
    // Setters
    public void setMoving(boolean moving) { this.isMoving = moving; }
    public void setAtStation(boolean atStation) { this.isAtStation = atStation; }
    public void setMovingBetweenStations(boolean moving) { this.isMovingBetweenStations = moving; }
    public void setReturnTrip(boolean returnTrip) { this.isReturnTrip = returnTrip; }
    public void setCurrentStationIndex(int index) { this.currentStationIndex = index; }
    public void setRemainingSteps(int steps) { this.remainingSteps = steps; }
    public void setStationWaitTime(int time) { this.stationWaitTime = time; }
    public void incrementStationWaitTime() { this.stationWaitTime++; }
    public void setVehicleId(int vehicleId ) { this.vehicleId = vehicleId; }

} 