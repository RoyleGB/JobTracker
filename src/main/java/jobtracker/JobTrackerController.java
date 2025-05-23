package jobtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.DatabaseConnection.DATABASE_URL;

@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access backend locally
@RestController
public class JobTrackerController {

    @Autowired
    private JobRepository jobRepository;

    // Landing page
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot";
    }

    // Get all jobs
    @GetMapping("/all-jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    // Add new job
    @PostMapping("/add-job")
    public ResponseEntity<Job> addJob(@RequestBody Job job) {
        Job savedJob = jobRepository.save(job);

        return ResponseEntity.ok(savedJob);
    }

    @DeleteMapping("/delete-job/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable("id") int id) {
        if (!jobRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        jobRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
