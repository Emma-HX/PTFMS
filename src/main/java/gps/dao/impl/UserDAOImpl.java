package gps.dao.impl;

import gps.dao.UserDAO;
import gps.dto.UserDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection;
    
    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public UserDTO getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDTO(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getInt("user_type")
                );
            }
        }
        return null;
    }
    
    @Override
    public UserDTO getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDTO(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getInt("user_type")
                );
            }
        }
        return null;
    }
    
    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new UserDTO(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getInt("user_type")
                ));
            }
        }
        return users;
    }
    
    @Override
    public boolean addUser(UserDTO user) throws SQLException {
        String sql = "INSERT INTO Users (name, email, password_hash, user_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setInt(4, user.getUserType());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean updateUser(UserDTO user) throws SQLException {
        String sql = "UPDATE Users SET name = ?, email = ?, password_hash = ?, user_type = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setInt(4, user.getUserType());
            stmt.setInt(5, user.getUserId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    @Override
    public boolean deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        }
    }
} 