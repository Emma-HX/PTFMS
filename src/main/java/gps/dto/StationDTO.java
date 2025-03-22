package gps.dto;
/**
 * station entity DTO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class StationDTO {
    private int stationId;
    private String name;
    private String location;
    private String description;
    
    // 构造函数
    public StationDTO(int stationId, String name, String location, String description) {
        this.stationId = stationId;
        this.name = name;
        this.location = location;
        this.description = description;
    }
    
    // Getters and Setters
    public int getStationId() { return stationId; }
    public void setStationId(int stationId) { this.stationId = stationId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
} 