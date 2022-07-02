package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
  @Query("Select p from ProjectEntity p where p.active = true")
  List<ProjectEntity> findAllActive();

  @Query("Select p from ProjectEntity p where p.active = true")
  Page<ProjectEntity> findAllActive(Pageable pageable);
}
