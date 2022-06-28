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
}
