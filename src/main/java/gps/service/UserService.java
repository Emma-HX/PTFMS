package gps.service;

import gps.dto.UserDTO;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    UserDTO getUserById(int userId) throws SQLException;
    UserDTO getUserByUsername(String username) throws SQLException;
    List<UserDTO> getAllUsers() throws SQLException;
    boolean addUser(UserDTO user) throws SQLException;
    boolean updateUser(UserDTO user) throws SQLException;
    boolean deleteUser(int userId) throws SQLException;
    boolean validateUser(String username, String password) throws SQLException;
} 