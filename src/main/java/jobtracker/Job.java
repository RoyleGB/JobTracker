package jobtracker;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "jobs") // Tells hibernate which table to use
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company")
    private String company;

    @Column(name = "title")
    private String jobTitle;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private String dateApplied;

    @Column(name = "notes")
    private String notes;

    // Main Constructor
    public Job(int id, String company, String jobTitle, String status, String dateApplied) {
        this.id = id;
        this.company = company;
        this.jobTitle = jobTitle;
        this.status = status;
        this.dateApplied = dateApplied;
    }

    // Constructor with Notes
    public Job(int id, String company, String jobTitle, String status, String dateApplied, String notes) {
        this.id = id;
        this.company = company;
        this.jobTitle = jobTitle;
        this.status = status;
        this.dateApplied = dateApplied;
        this.notes = notes;
    }

    public Job() {

    }

    // ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // COMPANY
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    // JOB TITLE
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // STATUS
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // APPLIED DATE
    public String getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    // NOTES
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
