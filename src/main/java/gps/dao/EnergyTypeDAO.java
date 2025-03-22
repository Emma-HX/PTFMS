package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.EnergyTypeDTO;
/**
 * EnergyType DAO
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface EnergyTypeDAO {
    EnergyTypeDTO getEnergyTypeById(int energyId) throws SQLException;
    List<EnergyTypeDTO> getAllEnergyTypes() throws SQLException;
    boolean addEnergyType(EnergyTypeDTO energyType) throws SQLException;
    boolean updateEnergyType(EnergyTypeDTO energyType) throws SQLException;
    boolean deleteEnergyType(int energyId) throws SQLException;
} 