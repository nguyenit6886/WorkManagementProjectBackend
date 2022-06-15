package huce.edu.workmanagementprojectbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"huce.edu"})
public class WorkManagementProjectBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(WorkManagementProjectBackendApplication.class, args);
  }

}
