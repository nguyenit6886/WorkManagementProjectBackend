package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Integer> {
  @Query("Select a from AssignmentEntity a where a.active = true")
  List<AssignmentEntity> findAllActive();

  @Query("Select a from AssignmentEntity a where a.task.id = ?1")
  List<AssignmentEntity> findAllByTask(int taskId);

  @Query("Select a from AssignmentEntity a where a.active = true and a.task.id = ?1")
  List<AssignmentEntity> findAllByTaskActive(int taskId);
}
