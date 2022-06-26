package huce.edu.workmanagementprojectbackend.services.workprogress;

import huce.edu.workmanagementprojectbackend.model.TaskEntity;
import huce.edu.workmanagementprojectbackend.model.WorkProgressEntity;
import huce.edu.workmanagementprojectbackend.repository.TaskRepository;
import huce.edu.workmanagementprojectbackend.repository.WorkProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkProgressServiceImpl implements IWorkProgressService{

  @Autowired
  private WorkProgressRepository repository;

  @Autowired
  private TaskRepository taskRepository;

  @Override
  public List<WorkProgressEntity> getAll() {
    return repository.findAllActive();
  }

  @Override
  public WorkProgressEntity getObjectById(int id) {
    return repository.findById(id).get();
  }

  @Override
  public int insertObject(WorkProgressEntity workProgressEntity) {
    return 0;
  }

  @Override
  public int updateObject(WorkProgressEntity workProgressEntity) {
    return 0;
  }

  @Override
  public int deleteObject(int id) {
    return 0;
  }

  @Override
  public List<WorkProgressEntity> getObjectsByTask(int taskId) {
    TaskEntity taskEntity = taskRepository.findById(taskId).get();
    return repository.findAll().stream()
        .filter(w -> w.getTask().equals(taskEntity)).collect(Collectors.toList());
  }
}
