package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkProgressRepository extends JpaRepository<WorkProgressEntity, Integer> {
  @Query("Select w from WorkProgressEntity w where w.active = true")
  List<WorkProgressEntity> findAllActive();

  @Query("Select t from WorkProgressEntity t where t.active = true and t.task.id = :#{#taskId}")
  Page<WorkProgressEntity> findAllWorkProgressByProjectId(int taskId, Pageable pageable);
}
