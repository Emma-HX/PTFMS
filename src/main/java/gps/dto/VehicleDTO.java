package gps.dto;

import java.sql.Timestamp;

public class VehicleDTO {
    private int vehicleId;
    private int vehicleTypeId;
    private String vehicleNumber;
    private int energyId;
    private float maxConsumptionRate;
    private int maxPassengers;
    private Timestamp createdAt;
    
    public VehicleDTO(int vehicleId, int vehicleTypeId, String vehicleNumber, int energyId,
                     float maxConsumptionRate, int maxPassengers, Timestamp createdAt) {
        this.vehicleId = vehicleId;
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleNumber = vehicleNumber;
        this.energyId = energyId;
        this.maxConsumptionRate = maxConsumptionRate;
        this.maxPassengers = maxPassengers;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    
    public int getVehicleTypeId() { return vehicleTypeId; }
    public void setVehicleTypeId(int vehicleTypeId) { this.vehicleTypeId = vehicleTypeId; }
    
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    
    public int getEnergyId() { return energyId; }
    public void setEnergyId(int energyId) { this.energyId = energyId; }
    
    public float getMaxConsumptionRate() { return maxConsumptionRate; }
    public void setMaxConsumptionRate(float maxConsumptionRate) { this.maxConsumptionRate = maxConsumptionRate; }
    
    public int getMaxPassengers() { return maxPassengers; }
    public void setMaxPassengers(int maxPassengers) { this.maxPassengers = maxPassengers; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
} 