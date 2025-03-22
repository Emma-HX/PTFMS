package gps.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import gps.dao.ManualLogDAO;
import gps.dto.ManualLogDTO;
import gps.util.DataSource;

public class ManualLogDAOImpl implements ManualLogDAO {

    // Insert a new manual log record into the database
    @Override
    public void addManualLog(ManualLogDTO manualLog) {
        String sql = "INSERT INTO manual_logs (trip_id, station_id, vehicle_id, report_type, report_time, break_time, reason) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the SQL query
            stmt.setString(1, manualLog.getTripId());
            stmt.setInt(2, manualLog.getStationId());
            stmt.setInt(3, manualLog.getVehicleId());
            stmt.setString(4, manualLog.getReportType());
            stmt.setTimestamp(5, manualLog.getReportTime());
            stmt.setInt(6, manualLog.getBreakTime());
            stmt.setString(7, manualLog.getReason());

            stmt.executeUpdate();  // Execute the SQL insert
            System.out.println("Log inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();  // Print SQL exception stack trace
        }
    }

    // Retrieve a manual log record by its ID
    @Override
    public ManualLogDTO getManualLogById(int logId) {
        String sql = "SELECT * FROM manual_logs WHERE log_id = ?";
        ManualLogDTO log = null;

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, logId);  // Set the log ID parameter
            ResultSet rs = stmt.executeQuery();  // Execute the SQL query

            if (rs.next()) {
                // Map the result set to a ManualLogDTO object
//                log = new ManualLogDTO(
//                    rs.getInt("log_id"),
//                    rs.getInt("trip_id"),
//                    rs.getInt("station_id"),
//                    rs.getInt("vehicle_id"),
//                    rs.getString("report_type"),
//                    rs.getTimestamp("report_time"),
//                    rs.getInt("break_time"),
//                    rs.getString("reason")
//                );
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print SQL exception stack trace
        }
        return log;
    }

    // Retrieve all manual log records from the database
    @Override
    public List<ManualLogDTO> getAllManualLogs() {
        String sql = "SELECT * FROM manual_logs";
        List<ManualLogDTO> logs = new ArrayList<>();  // List to store all log records

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Map each row in the result set to a ManualLogDTO object and add it to the list
//                ManualLogDTO log = new ManualLogDTO(
//                    rs.getInt("log_id"),
//                    rs.getInt("trip_id"),
//                    rs.getInt("station_id"),
//                    rs.getInt("vehicle_id"),
//                    rs.getString("report_type"),
//                    rs.getTimestamp("report_time"),
//                    rs.getInt("break_time"),
//                    rs.getString("reason")
//                );
//                logs.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print SQL exception stack trace
        }
        return logs;
    }

    // Update an existing manual log record in the database
    @Override
    public void updateManualLog(ManualLogDTO manualLog) {
        String sql = "UPDATE manual_logs SET trip_id = ?, station_id = ?, vehicle_id = ?, report_type = ?, " +
                     "report_time = ?, break_time = ?, reason = ? WHERE log_id = ?";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the SQL update query
            stmt.setString(1, manualLog.getTripId());
            stmt.setInt(2, manualLog.getStationId());
            stmt.setInt(3, manualLog.getVehicleId());
            stmt.setString(4, manualLog.getReportType());
            stmt.setTimestamp(5, manualLog.getReportTime());
            stmt.setInt(6, manualLog.getBreakTime());
            stmt.setString(7, manualLog.getReason());
            stmt.setInt(8, manualLog.getLogId());

            stmt.executeUpdate();  // Execute the SQL update
            System.out.println("Log updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();  // Print SQL exception stack trace
        }
    }

    // Delete a manual log record from the database by its ID
    @Override
    public void deleteManualLog(int logId) {
        String sql = "DELETE FROM manual_logs WHERE log_id = ?";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, logId);  // Set the log ID parameter
            stmt.executeUpdate();  // Execute the SQL delete
            System.out.println("Log deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();  // Print SQL exception stack trace
        }
    }
}

