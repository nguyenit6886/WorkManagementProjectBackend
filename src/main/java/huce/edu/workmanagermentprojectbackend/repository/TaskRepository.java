package huce.edu.workmanagermentprojectbackend.repository;

import huce.edu.workmanagermentprojectbackend.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
