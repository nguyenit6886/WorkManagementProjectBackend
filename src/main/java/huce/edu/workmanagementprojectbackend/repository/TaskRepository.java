package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
  @Query("Select t from TaskEntity t where t.active = true order by t.createDate desc")
  List<TaskEntity> findAllActive();

  @Query("Select t from TaskEntity t where t.active = true and t.project.id = :#{#projectId} order by t.createDate desc")
  List<TaskEntity> findAllTasksByProjectId(int projectId);

  @Query("Select t from TaskEntity t where t.active = true and t.project.id = :#{#projectId} order by t.createDate desc")
  Page<TaskEntity> findAllTasksByProjectId(int projectId, Pageable pageable);
}
