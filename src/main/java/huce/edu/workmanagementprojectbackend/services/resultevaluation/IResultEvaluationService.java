package huce.edu.workmanagementprojectbackend.services.resultevaluation;

import huce.edu.workmanagementprojectbackend.model.ResultEvaluationEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IResultEvaluationService extends IFunctionService<ResultEvaluationEntity> {
  @Override
  List<ResultEvaluationEntity> getAll();

  @Override
  ResultEvaluationEntity getObjectById(int id);

  @Override
  int insertObject(ResultEvaluationEntity resultEvaluationEntity);

  @Override
  int updateObject(ResultEvaluationEntity resultEvaluationEntity);

  @Override
  int deleteObject(int id);
}
