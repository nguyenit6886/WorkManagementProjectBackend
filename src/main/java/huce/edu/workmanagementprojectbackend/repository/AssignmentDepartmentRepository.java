package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.AssignmentDepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDepartmentRepository extends JpaRepository<AssignmentDepartmentEntity, Integer> {
  @Query("Select a from AssignmentDepartmentEntity a where a.active = true")
  List<AssignmentDepartmentEntity> findAllActive();
}
