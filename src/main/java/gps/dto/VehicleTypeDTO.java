package gps.dto;

public class VehicleTypeDTO {
    private int typeId;
    private String typeName;
    private String description;
    
    public VehicleTypeDTO(int typeId, String typeName, String description) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.description = description;
    }
    
    // Getters and Setters
    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }
    
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
} 