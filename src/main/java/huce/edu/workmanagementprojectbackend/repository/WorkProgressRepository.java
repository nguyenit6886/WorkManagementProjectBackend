package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkProgressRepository extends JpaRepository<WorkProgressEntity, Integer> {
  @Query("Select w from WorkProgressEntity w where w.active = true")
  List<WorkProgressEntity> findAllActive();
}
