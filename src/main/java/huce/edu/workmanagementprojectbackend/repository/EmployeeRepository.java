package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
  @Query("Select e from EmployeeEntity e where e.active = true")
  List<EmployeeEntity> findAllActive();

  @Query("Select e from EmployeeEntity e where e.active = true")
  Page<EmployeeEntity> findAllActive(Pageable pageable);
}
