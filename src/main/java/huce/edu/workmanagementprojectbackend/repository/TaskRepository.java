package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
  @Query("Select t from TaskEntity t where t.active = true")
  List<TaskEntity> findAllActive();

  @Query("Select t from TaskEntity t where t.active = true and t.project.id = :#{#projectId}")
  List<TaskEntity> findAllTasksByProjectId(int projectId);
}
