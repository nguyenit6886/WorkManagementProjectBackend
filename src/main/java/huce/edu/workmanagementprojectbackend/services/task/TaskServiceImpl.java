package huce.edu.workmanagementprojectbackend.services.task;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService{

  @Autowired
  private TaskRepository repository;

  @Override
  public List<TaskEntity> getAll() {
    return repository.findAllActive();
  }

  @Override
  public TaskEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(TaskEntity taskEntity) {
    return 0;
  }

  @Override
  public int updateObject(TaskEntity taskEntity) {
    return 0;
  }

  @Override
  public int deleteObject(int id) {
    return 0;
  }
}
