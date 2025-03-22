package gps.dto;

/**
 * Builder class for ManualLogDTO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class ManualLogDTOBuilder {
    private final ManualLogDTO dto;

    public ManualLogDTOBuilder() {
        dto = new ManualLogDTO();
    }

    public ManualLogDTOBuilder tripId(String tripId) {
        dto.setTripId(tripId);
        return this;
    }

    public ManualLogDTOBuilder stationId(int stationId) {
        dto.setStationId(stationId);
        return this;
    }

    public ManualLogDTOBuilder vehicleId(int vehicleId) {
        dto.setVehicleId(vehicleId);
        return this;
    }

    public ManualLogDTOBuilder reportType(String reportType) {
        dto.setReportType(reportType);
        return this;
    }

    public ManualLogDTOBuilder breakTime(int breakTime) {
        dto.setBreakTime(breakTime);
        return this;
    }

    public ManualLogDTOBuilder reason(String reason) {
        dto.setReason(reason);
        return this;
    }

    public ManualLogDTO build() {
        return dto;
    }
} 