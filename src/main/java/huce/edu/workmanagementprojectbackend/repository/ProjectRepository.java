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
  @Query("Select p from ProjectEntity p where p.active = true order by p.createDate desc")
  List<ProjectEntity> findAllActive();

  @Query("Select p from ProjectEntity p where p.active = true order by p.createDate desc")
  Page<ProjectEntity> findAllActive(Pageable pageable);

  @Query("Select p from ProjectEntity p, AssignmentDepartmentEntity a where p.id = a.project.id and p.active = true and a.active = true and a.department.id = ?1")
  Page<ProjectEntity> findAllByDepartmentActive(Pageable pageable, int departmentId);
}
