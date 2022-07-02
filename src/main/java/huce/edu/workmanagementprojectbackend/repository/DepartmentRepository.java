package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
  @Query("Select d from DepartmentEntity d where d.active = true")
  List<DepartmentEntity> findAllActive();

  @Query("Select d from DepartmentEntity d where d.active = true")
  Page<DepartmentEntity> findAllActive(Pageable pageable);
}
