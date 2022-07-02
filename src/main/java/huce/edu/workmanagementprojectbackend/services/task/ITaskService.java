package huce.edu.workmanagementprojectbackend.services.task;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.paging.Paged;
import huce.edu.workmanagementprojectbackend.services.IFunctionService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITaskService extends IFunctionService<TaskEntity> {
  @Override
  List<TaskEntity> getAll();

  List<TaskEntity> getAllTasksByProjectId(int projectId);
  @Override
  TaskEntity getObjectById(int id);

  @Override
  int insertObject(TaskEntity taskEntity);

  @Override
  int updateObject(TaskEntity taskEntity);

  @Override
  int deleteObject(int id);

  @Override
  Paged<TaskEntity> getPage(int pageNumber);

  Paged<TaskEntity> getPageByProjectId(int projectId, int pageNumber);
}
