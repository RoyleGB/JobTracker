package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static database.DatabaseConnection.DATABASE_URL;

public class DatabaseHandler {

    public DatabaseHandler() {
        String sql = "CREATE TABLE IF NOT EXISTS jobs (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "company TEXT, \n" +
                "title TEXT, \n" +
                "status TEXT, \n" +
                "date TEXT, \n" +
                "notes TEXT\n" +
                ");";


        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully.\n");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
