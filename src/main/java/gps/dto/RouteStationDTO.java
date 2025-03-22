package gps.dto;

/**
 * routeStaion entity DTO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class RouteStationDTO {
    private int routeId;
    private int stationId;
    private int sequence;
    
    public RouteStationDTO(int routeId, int stationId, int sequence) {
        this.routeId = routeId;
        this.stationId = stationId;
        this.sequence = sequence;
    }
    
    // Getters and Setters
    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }
    
    public int getStationId() { return stationId; }
    public void setStationId(int stationId) { this.stationId = stationId; }
    
    public int getSequence() { return sequence; }
    public void setSequence(int sequence) { this.sequence = sequence; }
} 