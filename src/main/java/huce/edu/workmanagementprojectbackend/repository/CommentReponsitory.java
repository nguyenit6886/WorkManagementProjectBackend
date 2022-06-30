package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReponsitory extends JpaRepository<CommentEntity, Integer> {
  @Query("Select c from CommentEntity c where c.active = true")
  List<CommentEntity> findAllActive();
}
