package gps.service.impl;

import gps.dao.ManualLogDAO;
import gps.dao.impl.ManualLogDAOImpl;
import gps.dto.ManualLogDTO;
import gps.service.ManualLogService;

import java.sql.SQLException;
import java.util.List;

/**
 * ManualLogService's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
 */
public class ManualLogServiceImpl implements ManualLogService {
    
    // Instance of the DAO to interact with the database
    private ManualLogDAO manualLogDAO;

    // Constructor initializing the DAO
    public ManualLogServiceImpl() {
        this.manualLogDAO = new ManualLogDAOImpl();  // Create a new instance of DAO
    }

    // Add a new manual log record to the database
    @Override
    public void addManualLog(ManualLogDTO manualLog) throws SQLException {
        manualLogDAO.addManualLog(manualLog);  // Call DAO method to add the log
    }

    // Retrieve a manual log record by its ID
    @Override
    public ManualLogDTO getManualLogById(int logId) throws SQLException {
        return manualLogDAO.getManualLogById(logId);  // Call DAO method to get the log by ID
    }

    // Retrieve all manual log records from the database
    @Override
    public List<ManualLogDTO> getAllManualLogs() throws SQLException {
        return manualLogDAO.getAllManualLogs();  // Call DAO method to get all logs
    }

    // Update an existing manual log record in the database
    @Override
    public void updateManualLog(ManualLogDTO manualLog) throws SQLException {
        manualLogDAO.updateManualLog(manualLog);  // Call DAO method to update the log
    }

    // Delete a manual log record from the database by its ID
    @Override
    public void deleteManualLog(int logId) throws SQLException {
        manualLogDAO.deleteManualLog(logId);  // Call DAO method to delete the log by ID
    }
}
