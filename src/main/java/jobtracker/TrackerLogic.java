package jobtracker;

import database.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

import static database.DatabaseConnection.DATABASE_URL;

public class TrackerLogic {


    DatabaseConnection dbConnection;


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
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addNewJob();
                case 2 -> findJob();
                case 3 -> getAllJobs();
                case 4 -> updateJob();
                case 5 -> deleteJob();
                case 6 -> {
                    logout();
                    return;
                }
                default -> System.out.println("Invalid Response");
            }
        }
    }

    public void logout() {
        System.out.println("Logged Out...");
    }

    public void deleteJob() {
        String sql = "DELETE FROM jobs WHERE id = ?";
        System.out.println("\n=== Job Delete Menu ===");
        System.out.println("\nEnter the Job ID to delete:");

        Scanner input = new Scanner(System.in);
        int jobId = input.nextInt();

        System.out.println("YOU ARE ABOUT TO DELETE THE JOB WITH ID: " + jobId);
        System.out.println("Are you sure you want to continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = input.nextInt();
        if(choice != 1){
            mainMenu();
        } else {
            try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, jobId);
                int rowsDeleted = pstmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("\nJob Deleted Successfully.");
                    mainMenu();
                } else {
                    System.err.println("No job found with that ID.");
                }
            } catch (SQLException ex) {
                System.err.println("Failed to delete job." + ex.getMessage());
            }
        }
    }

    public void updateJob() {
        System.out.println("\n=== Job Update Menu ===");
        System.out.println("\nSelect a job ID to update: ");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        System.out.println("\nWhich field do you wish to change?");
        System.out.println("1. Company name");
        System.out.println("2. Job Title");
        System.out.println("3. Status");
        System.out.println("4. Date Applied");
        System.out.println("5. Notes");

        int fieldChoice = input.nextInt();

        switch (fieldChoice) {
            case 1 -> updateCompanyName(choice);
            case 2 -> updateJobTitle(choice);
            case 3 -> updateStatus(choice);
            case 4 -> updateDateApplied(choice);
            case 5 -> updateNotes(choice);
            default -> System.out.println("Invalid Choice");
        }
    }

    // Updates the company name
    public void updateCompanyName(int id){
        String sql = "UPDATE jobs SET company = ? WHERE id = ?";
        String newCompanyName;
        Scanner input = new Scanner(System.in);

        System.out.println("New Company Name: ");
        newCompanyName = input.nextLine();

        try(Connection conn = DriverManager.getConnection(DATABASE_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newCompanyName);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("\nJob Successfully Updated.");
                mainMenu();
            } else {
                System.err.println("\nNo Job Found with that ID.");
            }
        } catch (SQLException ex){
            System.err.println("Could not Update Field: " + ex.getMessage());
        }
    }

    // Updates Job Title
    public void updateJobTitle(int id){
        String sql = "UPDATE jobs SET title = ? WHERE id = ?";
        String newJobTitle;
        Scanner input = new Scanner(System.in);

        System.out.println("New Job Title: ");
        newJobTitle = input.nextLine();

        try(Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newJobTitle);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("\nJob Successfully Updated.");
                mainMenu();
            } else {
                System.err.println("\nNo Job Found with that ID.");
            }
        } catch (SQLException ex){
            System.err.println("Could not Update Field: " + ex.getMessage());
        }
    }

    // Updates Status
    public void updateStatus(int id){
        String sql = "UPDATE jobs SET status = ? WHERE id = ?";
        String newStatus;
        Scanner input = new Scanner(System.in);

        System.out.println("New Status: ");
        newStatus = input.nextLine();

        try(Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("\nJob Successfully Updated.");
                mainMenu();
            } else {
                System.err.println("\nNo Job Found with that ID.");
            }
        } catch (SQLException ex){
            System.err.println("Could not Update Field: " + ex.getMessage());
        }
    }

    // Updates Date Applied
    public void updateDateApplied(int id){
        String sql = "UPDATE jobs SET date = ? WHERE id = ?";
        String newDate;
        Scanner input = new Scanner(System.in);

        System.out.println("New Date: (dd-mm-yyyy)");
        newDate = input.nextLine();

        try(Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newDate);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("\nJob Successfully Updated.");
                mainMenu();
            } else {
                System.err.println("\nNo Job Found with that ID.");
            }
        } catch (SQLException ex){
            System.err.println("Could not Update Field: " + ex.getMessage());
        }
    }

    // Updates Notes
    public void updateNotes(int id){
        String sql = "UPDATE jobs SET notes = ? WHERE id = ?";
        String newNotes;
        Scanner input = new Scanner(System.in);

        System.out.println("New Notes: ");
        newNotes = input.nextLine();

        try(Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newNotes);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("\nJob Successfully Updated.");
                mainMenu();
            } else {
                System.err.println("\nNo Job Found with that ID.");
            }
        } catch (SQLException ex){
            System.err.println("Could not Update Field: " + ex.getMessage());
        }
    }

    // Gets all the jobs in database
    public void getAllJobs() {
        String sql = "SELECT * from jobs";
        System.out.println("===========================");
        System.out.println("    ---- ALL JOBS ----");
        System.out.println("===========================\n");

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
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
                if (notes != null) {
                    System.out.println("Notes:\n" + notes + "\n");
                } else {
                    System.out.println("Notes: N/A \n");
                }
            }
            System.out.println("======= END OF JOBS ========\n");
            goHome();

        } catch (SQLException e) {
            System.err.println("Failed to retrieve all jobs." + e.getMessage());
        }
    }

    public void findJob() {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Job Search Menu ===");
            System.out.println("1. Search by Company Name");
            System.out.println("2. Search by Job Title");
            System.out.println("3. Exit");

            while (!input.hasNextInt()) {
                System.err.println("Invalid input. Please enter a number.");
                input.next();
                findJob();
            }
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> jobByCompanyName();
                case 2 -> jobByJobTitle();
                case 3 -> {
                    mainMenu();
                }
                default -> System.err.println("Invalid choice.");
            }

        } while (true);

    }

    public void jobByCompanyName() {
        String sql = "SELECT * from jobs WHERE LOWER(company) LIKE LOWER(?)";
        Scanner input = new Scanner(System.in);


        System.out.println("\nEnter Company Name: ");
        String name = input.nextLine().trim();


        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // bind scanner input to the prepared statement
            pstmt.setString(1, "%"+ name + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println("\n======== Jobs By Company Name ========");

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
                    if (notes != null) {
                        System.out.println("Notes:\n" + notes);
                    } else {
                        System.out.println("Notes: N/A");
                    }
                    System.out.println("========= END OF JOBS ==========");
                }

                if (!found) {
                    System.err.println("No jobs found with company name: " + name);
                    jobByCompanyName();
                }
            }
            goHome();

        } catch (SQLException ex) {
            System.err.println("Could not retrieve jobs by company name: " + ex.getMessage());
        }
    }

    public void jobByJobTitle() {
        String sql = "SELECT * from jobs WHERE LOWER(title) LIKE LOWER(?)";

        Scanner input = new Scanner(System.in);


        System.out.println("\nEnter Job Title Keyword: ");
        String jobTitle = input.nextLine().trim();


        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // bind scanner input to the prepared statement
            pstmt.setString(1, "%"+ jobTitle + "%"); // % Allows to check for characters in between this for better finding

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n======== Jobs By Job Title ========");
                boolean found = false;

                while (rs.next()) {
                    found = true;

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
                    if (notes != null) {
                        System.out.println("Notes:\n" + notes + "\n");
                    } else {
                        System.out.println("Notes: N/A \n");
                    }
                }
                System.out.println("========= END OF JOBS ==========");
                goHome();

                if (!found) {
                    System.err.println("No jobs found with job title: " + jobTitle);
                    jobByJobTitle();
                }
            }

        } catch (SQLException ex) {
            System.err.println("Could not retrieve jobs by job title: " + ex.getMessage());
        }
    }

    // Adds a new job
    public void addNewJob() {
        Scanner input = new Scanner(System.in);
        int choice;
        String company, title, status, date, notes;
        String sql = "INSERT INTO jobs (company, title, status, date) VALUES (?, ?, ?, ?)";
        String sqlWithNotes = "INSERT INTO jobs (company, title, status, date, notes) VALUES (?, ?, ?, ?, ?)";

        System.out.println("\n=== New Job Add ===");
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
        choice = input.nextInt();
        input.nextLine();

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

    public void goHome() {
        String exit;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nType 'Home' to exit.");
        do {
            exit = scanner.nextLine();

        } while (!exit.equalsIgnoreCase("Home"));
        mainMenu();
    }
}
