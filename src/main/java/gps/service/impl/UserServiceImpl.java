package gps.service.impl;

import gps.dao.UserDAO;
import gps.dao.impl.UserDAOImpl;
import gps.dto.UserDTO;
import gps.service.UserService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    
    public UserServiceImpl(Connection connection) {
        this.userDAO = new UserDAOImpl(connection);
    }
    
    @Override
    public UserDTO getUserById(int userId) throws SQLException {
        return userDAO.getUserById(userId);
    }
    
    
    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }
    
    @Override
    public boolean addUser(UserDTO user) throws SQLException {
        return userDAO.addUser(user);
    }
    
    @Override
    public boolean updateUser(UserDTO user) throws SQLException {
        return userDAO.updateUser(user);
    }
    
    @Override
    public boolean deleteUser(int userId) throws SQLException {
        return userDAO.deleteUser(userId);
    }

	@Override
	public UserDTO getUserByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
} 