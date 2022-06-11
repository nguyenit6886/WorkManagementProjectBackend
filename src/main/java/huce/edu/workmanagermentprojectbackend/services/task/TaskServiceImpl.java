package huce.edu.workmanagermentprojectbackend.services.task;

import huce.edu.workmanagermentprojectbackend.model.TaskEntity;
import huce.edu.workmanagermentprojectbackend.repository.TaskRepository;
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
