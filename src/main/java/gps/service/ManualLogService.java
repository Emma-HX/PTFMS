package gps.service;

import gps.dto.ManualLogDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * ManualLogService interface
 * Provides methods for managing manual log records.
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
 */
public interface ManualLogService {

    // Add a new manual log record
    void addManualLog(ManualLogDTO manualLog) throws SQLException;

    // Retrieve a manual log record by its ID
    ManualLogDTO getManualLogById(int logId) throws SQLException;

    // Retrieve all manual log records
    List<ManualLogDTO> getAllManualLogs() throws SQLException;

    // Update an existing manual log record
    void updateManualLog(ManualLogDTO manualLog) throws SQLException;

    // Delete a manual log record by its ID
    void deleteManualLog(int logId) throws SQLException;
}

