package huce.edu.workmanagermentprojectbackend.repository;

import huce.edu.workmanagermentprojectbackend.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
}
