package jobtracker;

import java.util.Date;

public class Job {
    private int id;
    private String company;
    private String jobTitle;
    private String status;
    private Date dateApplied;
    private String notes;

    // Main Constructor
    public Job(int id, String company, String jobTitle, String status, Date dateApplied) {
        this.id = id;
        this.company = company;
        this.jobTitle = jobTitle;
        this.status = status;
        this.dateApplied = dateApplied;
    }

    // Constructor with Notes
    public Job(int id, String company, String jobTitle, String status, Date dateApplied, String notes) {
        this.id = id;
        this.company = company;
        this.jobTitle = jobTitle;
        this.status = status;
        this.dateApplied = dateApplied;
        this.notes = notes;
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
    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
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
