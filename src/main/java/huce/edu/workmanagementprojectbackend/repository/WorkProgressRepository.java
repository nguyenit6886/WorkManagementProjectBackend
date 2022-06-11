package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkProgressRepository extends JpaRepository<WorkProgressEntity, Integer> {
}
