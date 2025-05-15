package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static String DATABASE_URL = "jdbc:sqlite:jobsdatabase.db";
    private Connection connection;

    // Database set up
    public DatabaseConnection() {
        connection = null;
        try {
            // Establish Connection
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Successfully connected to database.");
            new DatabaseHandler();
        } catch (SQLException ex) {
            System.err.println("Failed to connect to the database.");
            System.out.println(ex.getMessage());
        } finally {
            try{
                if(connection != null && connection.isClosed()){
                    connection.close();
                }
            } catch(SQLException e){
                System.out.println("Could not close the database connection.");
                System.out.println(e.getMessage());
            }
        }
    }

    // Returns the connection
    public Connection getConnection() {
        return connection;
    }
}
