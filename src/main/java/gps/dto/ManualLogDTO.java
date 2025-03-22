package gps.dto;

import java.sql.Timestamp;

/**
 * ManualLog entity DTO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class ManualLogDTO {
    private int logId;              
    private String tripId;            
    private int stationId;          
    private int vehicleId;          
    private String reportType;      
    private Timestamp reportTime; 
    private int breakTime;          
    private String reason;         

    // constructor
//    public ManualLogDTO() {
//    }
//
//    // constructor with parameter
//    public ManualLogDTO(int logId, int tripId, int stationId, int vehicleId, 
//                        String reportType, java.sql.Timestamp reportTime, 
//                        int breakTime, String reason) {
//        this.logId = logId;
//        this.tripId = tripId;
//        this.stationId = stationId;
//        this.vehicleId = vehicleId;
//        this.reportType = reportType;
//        this.reportTime = reportTime;
//        this.breakTime = breakTime;
//        this.reason = reason;
//    }
//    
//    public ManualLogDTO( int tripId, int stationId, int vehicleId, 
//            String reportType, java.sql.Timestamp reportTime, 
//            int breakTime, String reason) {
//		this.tripId = tripId;
//		this.stationId = stationId;
//		this.vehicleId = vehicleId;
//		this.reportType = reportType;
//		this.reportTime = reportTime;
//		this.breakTime = breakTime;
//		this.reason = reason;
//	}
    ManualLogDTO() {
        this.reportTime = new Timestamp(System.currentTimeMillis());
    }

    // Getter and Setter 
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public java.sql.Timestamp getReportTime() {
        return reportTime;
    }

    public void setReportTime(java.sql.Timestamp reportTime) {
        this.reportTime = reportTime;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ManualLogDTO{" +
                "logId=" + logId +
                ", tripId=" + tripId +
                ", stationId=" + stationId +
                ", vehicleId=" + vehicleId +
                ", reportType='" + reportType + '\'' +
                ", reportTime=" + reportTime +
                ", breakTime=" + breakTime +
                ", reason='" + reason + '\'' +
                '}';
    }
}
