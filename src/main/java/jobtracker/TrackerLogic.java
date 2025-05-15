package jobtracker;

import database.DatabaseConnection;
import database.DatabaseHandler;

import java.sql.*;
import java.util.Scanner;

import static database.DatabaseConnection.DATABASE_URL;

public class TrackerLogic {


    int choice = 0;
    Scanner scanner = new Scanner(System.in);
    DatabaseConnection dbConnection;
    DatabaseHandler dbHandler;

    public TrackerLogic() {
        try {
            dbConnection = new DatabaseConnection();
        } catch (Exception e) {
            System.out.println("Database failed to connect to tracker.\n");
        }
        mainMenu();
    }

    public void mainMenu() {
        // Main Menu
        System.out.println("===============================");
        System.out.println("    J O B   T R A C K E R");
        System.out.println("===============================\n");

        System.out.println("Select an option: ");
        System.out.println("1. Add New Job");
        System.out.println("2. Find a Job");
        System.out.println("3. Get all Jobs");
        System.out.println("4. Update a Job");
        System.out.println("5. Delete a Job");
        System.out.println("6. Log out");


        while (true) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addNewJob();
                case 2 -> findJob();
                case 3 -> getAllJobs();
                case 4 -> updateJob();
                case 5 -> DeleteJob();
                case 6 -> {
                    Logout();
                    return;
                }
                default -> System.out.println("Invalid Response");
            }
        }
    }

    public void Logout() {
        System.out.println("Logged Out");
    }

    public void DeleteJob() {

    }

    public void updateJob() {
        System.out.println("Updated Job");
    }

    public void getAllJobs(){
        String sql = "SELECT * from jobs";
        System.out.println("===========================");
        System.out.println("    ---- ALL JOBS ----");
        System.out.println("===========================\n");

        try(Connection conn = DriverManager.getConnection(DATABASE_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                // Loops through database and stores values in variables
                int id = rs.getInt("id");
                String company = rs.getString("company");
                String title = rs.getString("title");
                String status = rs.getString("status");
                String date = rs.getString("date");
                String notes = rs.getString("notes");

                System.out.println("ID: " + id);
                System.out.println("Company: " + company);
                System.out.println("Job Title: " + title);
                System.out.println("Status: " + status);
                System.out.println("Date Applied: " + date);
                if(notes != null) {
                    System.out.println("Notes:\n" + notes);
                } else {
                    System.out.println("Notes: N/A");
                }
                System.out.println("");
            }
            System.out.println("======= END OF JOBS ========\n");

            int choice;
            do {
                System.out.println("1. Go Home");
                choice = scanner.nextInt();
                scanner.nextLine();
            } while (choice != 1);

            mainMenu();

        } catch (SQLException e){
            System.err.println("Failed to retrieve all jobs.");
            e.getMessage();
        }
    }

    public void findJob() {
        System.out.println("Finding Job");
    }

    // Adds a new job
    public void addNewJob() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        String company, title, status, date, notes = "";
        String sql = "INSERT INTO jobs (company, title, status, date) VALUES (?, ?, ?, ?)";
        String sqlWithNotes = "INSERT INTO jobs (company, title, status, date, notes) VALUES (?, ?, ?, ?, ?)";

        System.out.println("Company name: ");
        company = input.nextLine();

        System.out.println("Job title: ");
        title = input.nextLine();


        System.out.println("Status: ");
        status = input.nextLine();

        System.out.println("Date (dd-mm-yyyy): ");
        date = input.nextLine();

        System.out.println("Any Notes?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Enter notes:");
            notes = input.nextLine();

            try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sqlWithNotes)) {

                pstmt.setString(1, company);
                pstmt.setString(2, title);
                pstmt.setString(3, status);
                pstmt.setString(4, date);
                pstmt.setString(5, notes);
                pstmt.executeUpdate();
                System.out.println("Added Job with notes.\n");
                mainMenu();

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else if (choice == 2) {
            try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, company);
                pstmt.setString(2, title);
                pstmt.setString(3, status);
                pstmt.setString(4, date);
                pstmt.executeUpdate();
                System.out.println("Added Job.\n");
                mainMenu();

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
