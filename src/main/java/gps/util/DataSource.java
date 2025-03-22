package gps.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Data source singleton class for managing database connections
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
 */
public class DataSource {    
    private static DataSource instance;
    private Connection connection;
    
    private DataSource() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get the DataSource instance
     * @return DataSource instance
     */
    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
    
    /**
     * Get database connection
     * @return database connection
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String[] info = getProperty();
            connection = DriverManager.getConnection(info[0], info[1], info[2]);
        }
        return connection;
    }
    
    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        /**
     * Get database info from file.
     * @return 
     */
    public static String[] getProperty(){
        Properties properties = new Properties();
        String[] info = new String[3];
        try(InputStream in = DataSource.class.getClassLoader().getResourceAsStream("database.properties")){
            properties.load(in);
        }catch(IOException e){
            System.err.println("Get Properties failed.");
            e.printStackTrace();
        }
        info[0] = properties.getProperty("jdbc.url");
        info[1] = properties.getProperty("jdbc.username");
        info[2] = properties.getProperty("jdbc.password");
        
        return info;
    }
} 