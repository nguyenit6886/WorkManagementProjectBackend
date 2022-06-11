package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReponsitory extends JpaRepository<CommentEntity, Integer> {
}
