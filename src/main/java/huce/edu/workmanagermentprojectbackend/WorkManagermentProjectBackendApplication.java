package huce.edu.workmanagermentprojectbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"huce.edu.workmanagermentprojectbackend.*"})
public class WorkManagermentProjectBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(WorkManagermentProjectBackendApplication.class, args);
  }

}
