package huce.edu.workmanagementprojectbackend.services.task;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskServiceImpl implements ITaskService{

  @Autowired
  private TaskRepository repository;

  @Override
  public List<TaskEntity> getAll() {
    return repository.findAll();
  }

  @Override
  public TaskEntity getObjectById(int id) {
    return repository.findById(id).get();
  }
}
