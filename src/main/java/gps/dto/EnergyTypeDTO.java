package gps.dto;

public class EnergyTypeDTO {
    private int energyId;
    private String energyName;
    private String description;
    
    public EnergyTypeDTO(int energyId, String energyName, String description) {
        this.energyId = energyId;
        this.energyName = energyName;
        this.description = description;
    }
    
    // Getters and Setters
    public int getEnergyId() { return energyId; }
    public void setEnergyId(int energyId) { this.energyId = energyId; }
    
    public String getEnergyName() { return energyName; }
    public void setEnergyName(String energyName) { this.energyName = energyName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
} 