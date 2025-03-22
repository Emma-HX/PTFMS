package gps.dto;

import java.sql.Timestamp;

/**
 * GPSData entity DTO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class GPSDataDTO {
    private int trackingId;
    private String tripId;
    private int routeId;
    private int vehicleId;
    private Integer stationId;
    private String location;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private String status;
    public GPSDataDTO () {
    	
    }
    public GPSDataDTO(String tripId, int routeId, int vehicleId, Integer stationId, String location,
                     Timestamp arrivalTime, Timestamp departureTime, String status) {
    	this.tripId = tripId;
    	this.routeId = routeId;
        this.vehicleId = vehicleId;
        this.stationId = stationId;
        this.location = location;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.status = status;
    }
    public GPSDataDTO(int trackingId, String tripId, int routeId,int vehicleId, Integer stationId, String location,
            Timestamp arrivalTime, Timestamp departureTime, String status) {
    	this.trackingId = trackingId;
    	this.tripId = tripId;
    	this.routeId = routeId;
		this.vehicleId = vehicleId;
		this.stationId = stationId;
		this.location = location;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.status = status;
	}
    
    // Getters and Setters
    public int getTrackingId() { return trackingId; }
    public void setTrackingId(int trackingId) { this.trackingId = trackingId; }
    
    public String getTripId() { return tripId;}
    public void setTripId(String tripId) { this.tripId = tripId; }
    
    public int getRouteId() { return routeId;}
    public void setRouteId(int routeId) { this.routeId = routeId; }   
    
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    
    public Integer getStationId() { return stationId; }
    public void setStationId(Integer stationId) { this.stationId = stationId; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Timestamp getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Timestamp arrivalTime) { this.arrivalTime = arrivalTime; }
    
    public Timestamp getDepartureTime() { return departureTime; }
    public void setDepartureTime(Timestamp departureTime) { this.departureTime = departureTime; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
} 