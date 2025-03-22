package gps.dao;

import java.util.List;

import gps.dto.ManualLogDTO;

/**
 * ManualLog DAO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface ManualLogDAO {

    // Insert a new manual log record into the database
    void addManualLog(ManualLogDTO manualLog);

    // Retrieve a manual log record by its ID
    ManualLogDTO getManualLogById(int logId);

    // Retrieve all manual log records from the database
    List<ManualLogDTO> getAllManualLogs();

    // Update an existing manual log record
    void updateManualLog(ManualLogDTO manualLog);

    // Delete a manual log record by its ID
    void deleteManualLog(int logId);
}

