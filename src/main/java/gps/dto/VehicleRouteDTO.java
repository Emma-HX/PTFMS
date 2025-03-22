package gps.dto;

public class VehicleRouteDTO {
    private int vehicleId;
    private int routeId;
    
    public VehicleRouteDTO(int vehicleId, int routeId) {
        this.vehicleId = vehicleId;
        this.routeId = routeId;
    }
    
    // Getters and Setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    
    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }
} 