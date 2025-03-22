package gps.dao;

import java.sql.SQLException;
import java.util.List;

import gps.dto.UserDTO;

public interface UserDAO {
    UserDTO getUserById(int userId) throws SQLException;
    UserDTO getUserByEmail(String email) throws SQLException;
    List<UserDTO> getAllUsers() throws SQLException;
    boolean addUser(UserDTO user) throws SQLException;
    boolean updateUser(UserDTO user) throws SQLException;
    boolean deleteUser(int userId) throws SQLException;
} 