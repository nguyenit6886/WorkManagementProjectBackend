package huce.edu.workmanagementprojectbackend.services.workprogress;

import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;

import java.util.List;

public interface IWorkProgressService extends IFunctionService<WorkProgressEntity> {
  @Override
  List<WorkProgressEntity> getAll();

  @Override
  WorkProgressEntity getObjectById(int id);

  @Override
  int insertObject(WorkProgressEntity workProgressEntity);

  @Override
  int updateObject(WorkProgressEntity workProgressEntity);

  @Override
  int deleteObject(int id);

  List<WorkProgressEntity> getObjectsByTask(int taskId);
}
