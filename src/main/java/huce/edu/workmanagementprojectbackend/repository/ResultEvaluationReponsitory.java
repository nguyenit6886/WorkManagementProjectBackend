package huce.edu.workmanagementprojectbackend.repository;

import huce.edu.workmanagementprojectbackend.model.ResultEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultEvaluationReponsitory extends JpaRepository<ResultEvaluationEntity, Integer> {
}
