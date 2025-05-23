package jobtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobTracker {
    public static void main(String[] args) {

       /*
            Console based version
        */
       // new TrackerLogic();



       /*
            Spring boot version
        */
       SpringApplication.run(JobTracker.class, args);
    }
}
