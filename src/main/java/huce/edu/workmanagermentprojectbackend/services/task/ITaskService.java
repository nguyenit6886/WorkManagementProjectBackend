package huce.edu.workmanagermentprojectbackend.services.task;

import huce.edu.workmanagermentprojectbackend.model.TaskEntity;
import huce.edu.workmanagermentprojectbackend.services.IFunctionService;

import java.util.List;

public interface ITaskService extends IFunctionService<TaskEntity> {
  @Override
  List getAll();

  @Override
  TaskEntity getObjectById(int id);
}
