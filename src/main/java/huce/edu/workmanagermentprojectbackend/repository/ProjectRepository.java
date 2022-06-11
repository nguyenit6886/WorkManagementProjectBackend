package huce.edu.workmanagermentprojectbackend.repository;

import huce.edu.workmanagermentprojectbackend.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
}
