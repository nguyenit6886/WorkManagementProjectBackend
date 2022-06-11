package huce.edu.workmanagementprojectbackend.services.resultevaluation;

import huce.edu.workmanagementprojectbackend.model.ResultEvaluationEntity;
import huce.edu.workmanagementprojectbackend.repository.ResultEvaluationReponsitory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ResultEvaluationServiceImpl implements IResultEvaluationService{

  @Autowired
  private ResultEvaluationReponsitory reponsitory;

  @Override
  public List<ResultEvaluationEntity> getAll() {
    return reponsitory.findAll();
  }

  @Override
  public ResultEvaluationEntity getObjectById(int id) {
    return reponsitory.findById(id).get();
  }
}
