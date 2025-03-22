package gps.dto;

import java.util.List;
/**
 * route entity DTO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class RouteDTO {
    private int routeId;
    private String routeName;
    private int startStationId;
    private int endStationId;
    private float distance;
    private String description;
    private List<StationDTO> stations;
    
    /**
     * constructor
     * @param routeId
     * @param routeName
     * @param startStationId
     * @param endStationId
     * @param distance
     * @param description
     */
    public RouteDTO(int routeId, String routeName, int startStationId, int endStationId, 
                   float distance, String description) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.startStationId = startStationId;
        this.endStationId = endStationId;
        this.distance = distance;
        this.description = description;
    }
    
    // Getters and Setters
    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }
    
    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }
    
    public int getStartStationId() { return startStationId; }
    public void setStartStationId(int startStationId) { this.startStationId = startStationId; }
    
    public int getEndStationId() { return endStationId; }
    public void setEndStationId(int endStationId) { this.endStationId = endStationId; }
    
    public float getDistance() { return distance; }
    public void setDistance(float distance) { this.distance = distance; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<StationDTO> getStations() { return stations; }
    public void setStations(List<StationDTO> stations) { this.stations = stations; }
} 