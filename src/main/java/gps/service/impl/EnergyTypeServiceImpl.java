package gps.service.impl;

import gps.dao.EnergyTypeDAO;
import gps.dao.impl.EnergyTypeDAOImpl;
import gps.dto.EnergyTypeDTO;
import gps.service.EnergyTypeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * EnergyTypeService's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class EnergyTypeServiceImpl implements EnergyTypeService {
    private EnergyTypeDAO energyTypeDAO;
    
    public EnergyTypeServiceImpl(Connection connection) {
        this.energyTypeDAO = new EnergyTypeDAOImpl(connection);
    }
    
    @Override
    public EnergyTypeDTO getEnergyTypeById(int energyId) throws SQLException {
        return energyTypeDAO.getEnergyTypeById(energyId);
    }
    
    @Override
    public List<EnergyTypeDTO> getAllEnergyTypes() throws SQLException {
        return energyTypeDAO.getAllEnergyTypes();
    }
    
    @Override
    public boolean addEnergyType(EnergyTypeDTO energyType) throws SQLException {
        return energyTypeDAO.addEnergyType(energyType);
    }
    
    @Override
    public boolean updateEnergyType(EnergyTypeDTO energyType) throws SQLException {
        return energyTypeDAO.updateEnergyType(energyType);
    }
    
    @Override
    public boolean deleteEnergyType(int energyId) throws SQLException {
        return energyTypeDAO.deleteEnergyType(energyId);
    }
} 